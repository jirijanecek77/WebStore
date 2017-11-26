package cz.renovahodonin.webstore.controllers;

import cz.renovahodonin.webstore.constants.ServiceMapping;
import cz.renovahodonin.webstore.constants.ViewName;
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

import java.util.ArrayList;

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
    public void testGetView() throws Exception
    {

        //given
        Store store = new Store();
        store.setId(STORE_ID);
        when(storeService.findById(anyLong())).thenReturn(store);

        //when
        mockMvc.perform(get("/" + STORE_ID + ServiceMapping.STOREITEM))
                .andExpect(status().isOk())
                .andExpect(view().name(ViewName.STOREITEM))
                .andExpect(model().attributeExists("store"));

        //then
        verify(storeService, times(1)).findById(anyLong());
    }

    @Test
    public void testPostNewStoreItemForm() throws Exception
    {
        //given
        StoreItem storeItem = new StoreItem();
        storeItem.setId(2L);

        //when
        when(storeItemService.save(anyLong(), any())).thenReturn(storeItem);

        //then
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
        //given
        StoreItem storeItem = new StoreItem();
        storeItem.setId(2L);

        //when
        when(storeItemService.findById(anyLong())).thenReturn(storeItem);
        when(unitOfMeasureService.getView()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/" + STORE_ID + ServiceMapping.STOREITEM_POST + "/" + STORE_ITEM_ID + ServiceMapping.STOREITEM_UPDATE))
                .andExpect(status().isOk())
                .andExpect(view().name(ViewName.STOREITEM_NEW))
                .andExpect(model().attributeExists("storeId"))
                .andExpect(model().attributeExists("storeItem"))
                .andExpect(model().attributeExists("units"));

        //then
        verify(storeItemService, times(1)).findById(anyLong());
        verify(unitOfMeasureService, times(1)).getView();
    }

    @Test
    public void testGetNewStoreForm() throws Exception
    {
        //given
        when(unitOfMeasureService.getView()).thenReturn(new ArrayList<>());

        //when
        mockMvc.perform(get("/" + STORE_ID + ServiceMapping.STOREITEM_NEW))
                .andExpect(status().isOk())
                .andExpect(view().name(ViewName.STOREITEM_NEW))
                .andExpect(model().attributeExists("storeId"))
                .andExpect(model().attributeExists("storeItem"))
                .andExpect(model().attributeExists("units"));

        //then
        verify(unitOfMeasureService, times(1)).getView();
    }

    @Test
    public void testDeleteAction() throws Exception
    {
        mockMvc.perform(get("/" + STORE_ID + ServiceMapping.STOREITEM_POST + "/" + STORE_ITEM_ID + ServiceMapping.STOREITEM_DELETE))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/" + STORE_ID + "/items"));

        verify(storeItemService, times(1)).delete(anyLong());
    }

}
