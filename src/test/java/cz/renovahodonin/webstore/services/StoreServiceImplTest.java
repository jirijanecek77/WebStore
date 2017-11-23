package cz.renovahodonin.webstore.services;

import cz.renovahodonin.webstore.model.Store;
import cz.renovahodonin.webstore.repositories.StoreRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class StoreServiceImplTest
{
	private static final long STORE_ID = 1L;
	StoreServiceImpl storeService;

	@Mock
	StoreRepository storeRepository;

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

		storeService = new StoreServiceImpl(storeRepository);
	}

	@Test
	public void getViewTest() throws Exception
	{

		Store store = new Store();
		List<Store> storesData = new ArrayList<>();
		storesData.add(store);

		when(storeService.getView()).thenReturn(storesData);

		List<Store> stores = storeService.getView();

		assertEquals(stores.size(), 1);
		verify(storeRepository, times(1)).findAll();
	}

	@Test
	public void findByIdTest() throws Exception
	{
		Store store = new Store();
		store.setId(STORE_ID);
		Optional<Store> storeOptional = Optional.of(store);

		when(storeRepository.findById(anyLong())).thenReturn(storeOptional);

		Store storeReturned = storeService.findById(STORE_ID);

		assertNotNull("Null store returned", storeReturned);
		verify(storeRepository, times(1)).findById(anyLong());
		verify(storeRepository, never()).findAll();
	}

	@Test
	public void testSaveStore() throws Exception
	{
		//given
		Store store = new Store();
		store.setId(STORE_ID);

		when(storeRepository.save(any())).thenReturn(store);

		//when
		Store savedStore = storeService.saveStore(store);

		//then
		assertEquals(Long.valueOf(STORE_ID), savedStore.getId());
		verify(storeRepository, times(1)).save(any(Store.class));

	}

	@Test
	public void testDeleteStore() throws Exception
	{
		//when
		storeService.deleteStore(STORE_ID);

		//then
		verify(storeRepository, times(1)).deleteById(anyLong());
	}

}