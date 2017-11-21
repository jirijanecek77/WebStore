package cz.renovahodonin.webstore.controllers;

import cz.renovahodonin.webstore.model.Store;
import cz.renovahodonin.webstore.services.StoreService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * TODO: Enter a paragraph that summarizes what the class does and why someone might want to utilize it
 * <p>
 * © 2017 NetSuite Inc.
 *
 * @author jjanecek
 * @since 2017-11-21
 */
public class StoreItemControllerTest
{
	private static final Long STORE_ID = 10L;

	@Mock
	StoreService storeService;

	StoreItemController controller;
	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		controller = new StoreItemController(storeService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testMockMVC() throws Exception {

		Store store = new Store();
		store.setId(STORE_ID);

		when(storeService.findById(anyLong())).thenReturn(store);

		mockMvc.perform(get("/1/items"))
				.andExpect(status().isOk())
				.andExpect(view().name("/items"))
				.andExpect(model().attributeExists("store"));
	}

}
