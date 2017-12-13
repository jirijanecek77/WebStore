package cz.renovahodonin.webstore.services;

import cz.renovahodonin.webstore.api.v1.dto.StoreDto;
import cz.renovahodonin.webstore.api.v1.dto.StoreItemDto;
import cz.renovahodonin.webstore.api.v1.services.StoreServiceImpl;
import cz.renovahodonin.webstore.exceptions.ResourceNotFoundException;
import cz.renovahodonin.webstore.model.Store;
import cz.renovahodonin.webstore.model.StoreItem;
import cz.renovahodonin.webstore.model.UnitOfMeasure;
import cz.renovahodonin.webstore.repositories.StoreRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

public class StoreServiceImplTest
{
    private static final Long STORE_ID1 = 1L;
    private static final Long STORE_ID2 = 2L;
    private static final String STORE_NAME = "name";

    private StoreServiceImpl storeService;

    @Mock
    private StoreRepository storeRepository;

    private StoreDto storeDto;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        storeService = new StoreServiceImpl(storeRepository);
        storeDto = new StoreDto("store");
    }

    @Test
    public void testGetAllStores() throws Exception
    {

        //given
        Store store1 = new Store();
        store1.setId(STORE_ID1);

        Store store2 = new Store();
        store2.setId(STORE_ID2);

        List<Store> stores = Arrays.asList(store1, store2);
        given(storeRepository.findAll()).willReturn(stores);

        //when
        List<StoreDto> storeDtoList = storeService.getAllStores();

        //then
        then(storeRepository).should(times(1)).findAll();
        assertThat(storeDtoList.size(), is(equalTo(2)));
    }

    @Test
    public void testGetById() throws Exception
    {
        Store store = new Store();
        store.setId(STORE_ID1);
        Optional<Store> storeOptional = Optional.of(store);

        when(storeRepository.findById(anyLong())).thenReturn(storeOptional);

        StoreDto storeReturned = storeService.getStoreById(STORE_ID1);

        assertNotNull("Null store returned", storeReturned);
        verify(storeRepository, times(1)).findById(anyLong());
        verify(storeRepository, never()).findAll();
    }

    @Test
    public void testGetAllStoreItems() throws Exception
    {
        //given
        Store store = new Store();
        StoreItem item1 = new StoreItem();
        item1.setUnit(new UnitOfMeasure());
        store.addStoreItem(item1);

        StoreItem item2 = new StoreItem();
        item2.setUnit(new UnitOfMeasure());
        store.addStoreItem(item2);

        given(storeRepository.findById(STORE_ID1)).willReturn(Optional.of(store));

        //when
        List<StoreItemDto> storeItemDtoList = storeService.getAllStoreItemsByStore(STORE_ID1);

        //then
        then(storeRepository).should(times(1)).findById(anyLong());
        assertThat(storeItemDtoList.size(), is(equalTo(2)));
        verify(storeRepository, never()).findAll();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testFindByIdNotFound() throws Exception
    {
        when(storeRepository.findById(anyLong())).thenReturn(Optional.empty());

        storeService.getStoreById(STORE_ID1);
    }

    @Test
    public void testSaveStore() throws Exception
    {

        Store store = new Store();
        store.setId(STORE_ID2);
        store.setName(STORE_NAME);

        given(storeRepository.save(any(Store.class))).willReturn(store);

        //when
        StoreDto savedStoreDto = storeService.addStore(storeDto);

        //then
        // 'should' defaults to times = 1
        then(storeRepository).should().save(any(Store.class));
        assertThat(savedStoreDto.getName(), is(equalTo(STORE_NAME)));
    }

    @Test
    public void testDeleteStore() throws Exception
    {
        //when
        storeService.deleteStore(STORE_ID1);

        //then
        verify(storeRepository, times(1)).deleteById(anyLong());
    }

}