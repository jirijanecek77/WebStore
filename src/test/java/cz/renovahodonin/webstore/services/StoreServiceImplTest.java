package cz.renovahodonin.webstore.services;

import cz.renovahodonin.webstore.model.Store;
import cz.renovahodonin.webstore.model.StoreItem;
import cz.renovahodonin.webstore.repositories.StoreRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class StoreServiceImplTest
{
    private static final Long ID = 1L;

    StoreServiceImpl storeService;

    @Mock
    StoreRepository storeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        storeService = new StoreServiceImpl(storeRepository);
    }

    @Test
    public void getStores() throws Exception {

        Store store = new Store();
        List<Store> storesData = new ArrayList<>();
        storesData.add(store);

        when(storeService.getStores()).thenReturn(storesData);

        List<Store> stores = storeService.getStores();

        assertEquals(stores.size(), 1);
        verify(storeRepository, times(1)).findAll();
    }

    @Test
    public void getStoreItems() throws Exception
    {
        Store store = new Store();
        store.setId(ID);
        StoreItem item = new StoreItem();
        item.setStore(store);
        List<StoreItem> itemsData = new ArrayList<>();
        itemsData.add(item);

        when(storeService.getStoreItems(ID)).thenReturn(itemsData);

        List<StoreItem> items = storeService.getStoreItems(ID);

        assertEquals(items.size(), 1);
    }


}