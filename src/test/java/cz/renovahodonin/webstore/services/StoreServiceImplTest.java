package cz.renovahodonin.webstore.services;

import cz.renovahodonin.webstore.api.v1.dto.StoreDto;
import cz.renovahodonin.webstore.api.v1.services.StoreServiceImpl;
import cz.renovahodonin.webstore.exceptions.NotFoundException;
import cz.renovahodonin.webstore.model.Store;
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

    private StoreServiceImpl storeService;

    @Mock
    private StoreRepository storeRepository;

    private StoreDto storeDto_1;
    private StoreDto storeDto_2;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        storeService = new StoreServiceImpl(storeRepository);
        storeDto_1 = new StoreDto("Store 1");
        storeDto_2 = new StoreDto("Store 2");
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
    public void testFindById() throws Exception
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

    @Test(expected = NotFoundException.class)
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

        given(storeRepository.save(any(Store.class))).willReturn(store);

        //when
        StoreDto savedStoreDto = storeService.addStore(storeDto_1);

        //then
        // 'should' defaults to times = 1
        then(storeRepository).should().save(any(Store.class));
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