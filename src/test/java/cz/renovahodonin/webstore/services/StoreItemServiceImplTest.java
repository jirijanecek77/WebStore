package cz.renovahodonin.webstore.services;

import cz.renovahodonin.webstore.api.v1.dto.StoreDto;
import cz.renovahodonin.webstore.api.v1.dto.StoreItemDto;
import cz.renovahodonin.webstore.api.v1.dto.UnitOfMeasureDto;
import cz.renovahodonin.webstore.api.v1.services.StoreItemServiceImpl;
import cz.renovahodonin.webstore.exceptions.NotFoundException;
import cz.renovahodonin.webstore.model.Store;
import cz.renovahodonin.webstore.model.StoreItem;
import cz.renovahodonin.webstore.model.UnitOfMeasure;
import cz.renovahodonin.webstore.repositories.StoreItemRepository;
import cz.renovahodonin.webstore.repositories.StoreRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

public class StoreItemServiceImplTest
{
    private static final Long STORE_ID = 10L;
    private static final Long STORE_ITEM_ID1 = 1L;
    private static final Long STORE_ITEM_ID2 = 2L;
    private static final Long UNIT_ID = 30L;

    private StoreItemServiceImpl storeItemService;

    @Mock
    private StoreItemRepository storeItemRepository;
    @Mock
    private StoreRepository storeRepository;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        storeItemService = new StoreItemServiceImpl(storeRepository, storeItemRepository);
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

        given(storeRepository.findById(STORE_ID)).willReturn(Optional.of(store));

        //when
        List<StoreItemDto> storeItemDtoList = storeItemService.getAllStoreItemsByStore(STORE_ID);

        //then
        then(storeRepository).should(times(1)).findById(anyLong());
        assertThat(storeItemDtoList.size(), is(equalTo(2)));
        verify(storeItemRepository, never()).findAll();
    }

    @Test
    public void testGetStoreItemById() throws Exception
    {
        StoreItem item = new StoreItem();
        item.setStore(new Store());
        item.setUnit(new UnitOfMeasure());
        Optional<StoreItem> storeItemOptional = Optional.of(item);

        when(storeItemRepository.findById(anyLong())).thenReturn(storeItemOptional);

        StoreItemDto storeItemReturned = storeItemService.getStoreItemById(STORE_ITEM_ID1);

        assertNotNull("Null store item returned", storeItemReturned);
        verify(storeItemRepository, times(1)).findById(anyLong());
        verify(storeItemRepository, never()).findAll();
    }

    @Test(expected = NotFoundException.class)
    public void testGetStoreItemByIdNotFound() throws Exception
    {
        when(storeItemRepository.findById(anyLong())).thenReturn(Optional.empty());

        storeItemService.getStoreItemById(STORE_ITEM_ID1);
    }

    @Test
    public void testDelete() throws Exception
    {
        storeItemService.deleteStoreItem(STORE_ITEM_ID1);

        //then
        verify(storeItemRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void testSave() throws Exception
    {
        //given
        UnitOfMeasureDto unit = new UnitOfMeasureDto("unit");
        StoreDto storeDto = new StoreDto("store");

        StoreItemDto storeItemDto = new StoreItemDto();
        storeItemDto.setUnit(unit);
        storeItemDto.setName("name");
        storeItemDto.setStore(storeDto);

        StoreItem item = new StoreItem();
        item.setStore(new Store());
        item.setUnit(new UnitOfMeasure());

        when(storeItemRepository.save(any())).thenReturn(item);

        //when
        StoreItemDto savedStoreItem = storeItemService.saveStoreItem(STORE_ITEM_ID1, storeItemDto);

        //then
        verify(storeItemRepository, times(1)).save(any(StoreItem.class));
    }

}