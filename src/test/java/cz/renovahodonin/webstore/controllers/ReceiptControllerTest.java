package cz.renovahodonin.webstore.controllers;

import cz.renovahodonin.webstore.model.Store;
import cz.renovahodonin.webstore.services.StoreService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ReceiptControllerTest
{
    private static final Long STORE_ID = 1L;

    @Mock
    private StoreService storeService;

    private ReceiptController controller;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        controller = new ReceiptController(storeService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testMockMVC() throws Exception
    {

        Store store = new Store();
        store.setName("name");
        store.setId(STORE_ID);

        when(storeService.findById(anyLong())).thenReturn(store);

        mockMvc.perform(get("/" + STORE_ID + "/receipts"))
                .andExpect(status().isOk())
                .andExpect(view().name("/receipts"))
                .andExpect(model().attributeExists("store"))
                .andExpect(model().attributeExists("receipts"));
    }

}