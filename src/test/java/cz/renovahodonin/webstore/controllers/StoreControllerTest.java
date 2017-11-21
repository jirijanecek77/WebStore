package cz.renovahodonin.webstore.controllers;

import cz.renovahodonin.webstore.model.Store;
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

public class StoreControllerTest
{
    @Mock
    StoreService storeService;

    @Mock
    Model model;

    StoreController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new StoreController(storeService);
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getViewTest() throws Exception {

        //given
        List<Store> stores = new ArrayList<>();
        stores.add(new Store());

        Store store = new Store();
        store.setId(1L);

        stores.add(store);

        when(storeService.getView()).thenReturn(stores);

        ArgumentCaptor<List<Store>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        //when
        String viewName = controller.getView(model);


        //then
        assertEquals("index", viewName);
        verify(storeService, times(1)).getView();
        verify(model, times(1)).addAttribute(eq("stores"), argumentCaptor.capture());
        List<Store> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }

}