package cz.renovahodonin.webstore.controllers;

import cz.renovahodonin.webstore.constants.ServiceMapping;
import cz.renovahodonin.webstore.exceptions.NotFoundException;
import cz.renovahodonin.webstore.model.Store;
import cz.renovahodonin.webstore.services.StoreService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class StoreControllerTest
{

    private static final Long STORE_ID = 1L;

    @Mock
    private StoreService storeService;

    @Mock
    private Model model;

    private StoreController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        controller = new StoreController(storeService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ExceptionHandlingController())
                .build();
    }

    @Test
    public void testMockMVC() throws Exception
    {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name(StoreController.STORE));
    }

    @Test
    public void testGetView() throws Exception
    {

        //given
        List<Store> stores = new ArrayList<>();
        stores.add(new Store());

        Store store = new Store();
        store.setId(STORE_ID);

        stores.add(store);

        when(storeService.getView()).thenReturn(stores);

        ArgumentCaptor<List<Store>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        //when
        String viewName = controller.getView(model);

        //then
        assertEquals(StoreController.STORE, viewName);
        verify(storeService, times(1)).getView();
        verify(model, times(1)).addAttribute(eq("stores"), argumentCaptor.capture());
        List<Store> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }

    @Test
    public void testPostNewStoreForm() throws Exception
    {
        Store store = new Store();
        store.setId(2L);

        when(storeService.save(any())).thenReturn(store);

        mockMvc.perform(post(ServiceMapping.STORE_POST)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    public void testGetUpdateView() throws Exception
    {
        Store store = new Store();
        store.setId(2L);

        when(storeService.findById(anyLong())).thenReturn(store);

        mockMvc.perform(get("/" + STORE_ID + ServiceMapping.UPDATE))
                .andExpect(status().isOk())
                .andExpect(view().name(StoreController.STORE_NEW))
                .andExpect(model().attributeExists("store"));
    }

    @Test
    public void testUpdateStoreNotFound() throws Exception
    {
        Store store = new Store();
        store.setId(STORE_ID);

        when(storeService.findById(anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/" + STORE_ID + ServiceMapping.UPDATE))
                .andExpect(status().isNotFound())
                .andExpect(view().name(ExceptionHandlingController.ERROR_PAGE));
    }

    @Test
    public void testNumberFormatException() throws Exception
    {

        mockMvc.perform(get("/abc/" + ServiceMapping.UPDATE))
                .andExpect(status().isBadRequest())
                .andExpect(view().name(ExceptionHandlingController.ERROR_PAGE));
    }

    @Test
    public void testGetNewStoreForm() throws Exception
    {
        mockMvc.perform(get(ServiceMapping.STORE_NEW))
                .andExpect(status().isOk())
                .andExpect(view().name(StoreController.STORE_NEW))
                .andExpect(model().attributeExists("store"));
    }

    @Test
    public void testDeleteAction() throws Exception
    {
        mockMvc.perform(get("/" + STORE_ID + ServiceMapping.DELETE))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(storeService, times(1)).delete(anyLong());
    }

}