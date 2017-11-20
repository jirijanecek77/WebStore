package cz.renovahodonin.webstore.controllers;

import cz.renovahodonin.webstore.model.Store;
import cz.renovahodonin.webstore.model.StoreItem;
import cz.renovahodonin.webstore.services.StoreService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class StoresControllerTest
{
    private static final Long STORE_ID = 10L;

    @Mock
    StoreService storeService;

    @Mock
    Model model;

    StoresController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new StoresController(storeService);
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void showStores() throws Exception {

        //given
        List<Store> stores = new ArrayList<>();
        stores.add(new Store());

        Store store = new Store();
        store.setId(1L);

        stores.add(store);

        when(storeService.getStores()).thenReturn(stores);

        ArgumentCaptor<List<Store>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        //when
        String viewName = controller.showStores(model);


        //then
        assertEquals("index", viewName);
        verify(storeService, times(1)).getStores();
        verify(model, times(1)).addAttribute(eq("stores"), argumentCaptor.capture());
        List<Store> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }

    @Test
    public void showStoreItems() throws Exception {

        //given
        Store store = new Store();
        store.setId(STORE_ID);
        List<StoreItem> items = new ArrayList<>();
        StoreItem item1 = new StoreItem();
        item1.setStore(store);
        items.add(item1);
        StoreItem item2 = new StoreItem();
        item2.setId(1L);
        item2.setStore(store);
        items.add(item2);

        when(storeService.getStoreItems(STORE_ID)).thenReturn(items);

        ArgumentCaptor<List<StoreItem>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        //when
        String viewName = controller.showStoreItems(STORE_ID.toString(), model);

        //then
        assertEquals("/items", viewName);
        verify(storeService, times(1)).getStoreItems(STORE_ID);
        verify(model, times(1)).addAttribute(eq("store_items"), argumentCaptor.capture());
        List<StoreItem> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }
}