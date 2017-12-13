package cz.renovahodonin.webstore.services;

import cz.renovahodonin.webstore.api.v1.dto.StoreDto;
import cz.renovahodonin.webstore.api.v1.dto.StoreItemDto;
import cz.renovahodonin.webstore.api.v1.dto.UnitOfMeasureDto;
import cz.renovahodonin.webstore.api.v1.services.StoreItemServiceImpl;
import cz.renovahodonin.webstore.exceptions.ResourceNotFoundException;
import cz.renovahodonin.webstore.model.Store;
import cz.renovahodonin.webstore.model.StoreItem;
import cz.renovahodonin.webstore.model.UnitOfMeasure;
import cz.renovahodonin.webstore.repositories.StoreItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class StoreItemServiceImplTest
{
    private static final Long STORE_ITEM_ID = 1L;
    private static final String STORE_ITEM_NAME = "name";

    private StoreItemServiceImpl storeItemService;

    @Mock
    private StoreItemRepository storeItemRepository;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        storeItemService = new StoreItemServiceImpl(storeItemRepository);
    }

    @Test
    public void testGetStoreItemById() throws Exception
    {
        StoreItem item = new StoreItem();
        item.setStore(new Store());
        item.setUnit(new UnitOfMeasure());
        Optional<StoreItem> storeItemOptional = Optional.of(item);

        when(storeItemRepository.findById(anyLong())).thenReturn(storeItemOptional);

        StoreItemDto storeItemReturned = storeItemService.getStoreItemById(STORE_ITEM_ID);

        assertNotNull("Null store item returned", storeItemReturned);
        verify(storeItemRepository, times(1)).findById(anyLong());
        verify(storeItemRepository, never()).findAll();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetStoreItemByIdNotFound() throws Exception
    {
        when(storeItemRepository.findById(anyLong())).thenReturn(Optional.empty());

        storeItemService.getStoreItemById(STORE_ITEM_ID);
    }

    @Test
    public void testDelete() throws Exception
    {
        storeItemService.deleteStoreItem(STORE_ITEM_ID);

        //then
        verify(storeItemRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void testSave() throws Exception
    {
        //given
        StoreItemDto storeItemDto = new StoreItemDto(STORE_ITEM_NAME, new UnitOfMeasureDto(), new StoreDto());

        StoreItem item = new StoreItem();
        item.setName(STORE_ITEM_NAME);
        item.setStore(new Store());
        item.setUnit(new UnitOfMeasure());

        //when
        when(storeItemRepository.save(any())).thenReturn(item);
        StoreItemDto savedStoreItem = storeItemService.saveStoreItem(STORE_ITEM_ID, storeItemDto);

        //then
        verify(storeItemRepository, times(1)).save(any(StoreItem.class));
        assertThat(savedStoreItem.getName(), is(equalTo(STORE_ITEM_NAME)));
    }

}