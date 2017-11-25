package cz.renovahodonin.webstore.controllers;

import cz.renovahodonin.webstore.model.Store;
import cz.renovahodonin.webstore.model.StoreItem;
import cz.renovahodonin.webstore.services.StoreItemService;
import cz.renovahodonin.webstore.services.StoreService;
import cz.renovahodonin.webstore.services.UnitOfMeasureService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class StoreItemControllerTest
{
    private static final Long STORE_ID = 1L;
    private static final Long STORE_ITEM_ID = 2L;

    @Mock
    private StoreService storeService;

    @Mock
    private StoreItemService storeItemService;

    @Mock
    private UnitOfMeasureService unitOfMeasureService;

    private StoreItemController controller;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        controller = new StoreItemController(storeService, storeItemService, unitOfMeasureService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testMockMVC() throws Exception
    {

        Store store = new Store();
        store.setId(STORE_ID);

        when(storeService.findById(anyLong())).thenReturn(store);

        mockMvc.perform(get("/" + STORE_ID + "/items"))
                .andExpect(status().isOk())
                .andExpect(view().name("/storeitems"))
                .andExpect(model().attributeExists("store"));
    }

    @Test
    public void testPostNewStoreItemForm() throws Exception
    {
        StoreItem storeItem = new StoreItem();
        storeItem.setId(2L);

        when(storeItemService.save(anyLong(), any())).thenReturn(storeItem);

        mockMvc.perform(post("/" + STORE_ID + "/storeItem/")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/" + STORE_ID + "/items"));
    }

    @Test
    public void testGetUpdateView() throws Exception
    {
        StoreItem storeItem = new StoreItem();
        storeItem.setId(2L);

        when(storeItemService.findById(anyLong())).thenReturn(storeItem);

        mockMvc.perform(get("/" + STORE_ID + "/storeItem/" + STORE_ITEM_ID + "/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("/storeitemform"))
                .andExpect(model().attributeExists("storeItem"))
                .andExpect(model().attributeExists("units"));
    }

    @Test
    public void testGetNewStoreForm() throws Exception
    {
        mockMvc.perform(get("/" + STORE_ID + "/storeItem/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("/storeitemform"))
                .andExpect(model().attributeExists("storeItem"))
                .andExpect(model().attributeExists("units"));;
    }

    @Test
    public void testDeleteAction() throws Exception
    {
        mockMvc.perform(get("/" + STORE_ID + "/storeItem/" + STORE_ITEM_ID + "/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/" + STORE_ID + "/items"));

        verify(storeItemService, times(1)).delete(anyLong());
    }

}
