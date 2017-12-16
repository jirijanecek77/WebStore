package cz.renovahodonin.webstore.controllers;

import cz.renovahodonin.webstore.api.v1.controllers.StoreController;
import cz.renovahodonin.webstore.api.v1.dto.ExceptionResponseDto;
import cz.renovahodonin.webstore.api.v1.dto.StoreDto;
import cz.renovahodonin.webstore.api.v1.dto.StoreItemDto;
import cz.renovahodonin.webstore.api.v1.services.StoreService;
import cz.renovahodonin.webstore.exceptions.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {StoreController.class})
public class StoreControllerTest
{

    private static final Long STORE_ID = 1L;

    @MockBean
    StoreService storeService;

    @Autowired
    MockMvc mockMvc; //provided by Spring Context

    private StoreDto storeDto_1;
    private StoreDto storeDto_2;

    @Before
    public void setUp() throws Exception
    {
        storeDto_1 = new StoreDto("Store 1");
        storeDto_2 = new StoreDto("Store 2");
    }

    @Test
    public void getStoreList() throws Exception
    {
        List<StoreDto> storeListDTO = Arrays.asList(storeDto_1, storeDto_2);

        when(storeService.getAllStores()).thenReturn(storeListDTO);

        mockMvc.perform(get(StoreController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void getStoreById() throws Exception
    {

        given(storeService.getStoreById(anyLong())).willReturn(storeDto_1);

        mockMvc.perform(get(StoreController.BASE_URL + "/" + STORE_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(storeDto_1.getName())));
    }

    @Test
    public void getStoreItemListByStore() throws Exception
    {
        List<StoreItemDto> storeItemListDTO = Arrays.asList(new StoreItemDto(), new StoreItemDto());

        when(storeService.getAllStoreItemsByStore(STORE_ID)).thenReturn(storeItemListDTO);

        mockMvc.perform(get(StoreController.BASE_URL + "/" + STORE_ID + "/storeitems")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void createNewStore() throws Exception
    {

        given(storeService.addStore(any(StoreDto.class))).willReturn(storeDto_1);
        given(storeService.validate(any(StoreDto.class), any(BindingResult.class))).willReturn(true);

        mockMvc.perform(post(StoreController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.asJsonString(storeDto_1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(storeDto_1.getName())));
    }

    @Test
    public void createStore_withInvalidName_Fails() throws Exception {

        given(storeService.validate(any(StoreDto.class), any(BindingResult.class))).willReturn(false);

        mockMvc.perform(post(StoreController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.asJsonString(new ExceptionResponseDto("code","msg"))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateStore() throws Exception
    {

        given(storeService.saveStore(anyLong(), any(StoreDto.class))).willReturn(storeDto_1);

        mockMvc.perform(put(StoreController.BASE_URL + "/" + STORE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.asJsonString(storeDto_1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(storeDto_1.getName())));
    }

    @Test
    public void patchStore() throws Exception
    {
        given(storeService.saveStore(anyLong(), any(StoreDto.class))).willReturn(storeDto_1);

        mockMvc.perform(patch(StoreController.BASE_URL + "/" + STORE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.asJsonString(storeDto_1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(storeDto_1.getName())));
    }

    @Test
    public void deleteStore() throws Exception
    {
        mockMvc.perform(delete(StoreController.BASE_URL + "/" + STORE_ID))
                .andExpect(status().isOk());

        then(storeService).should().deleteStore(anyLong());

    }

    @Test
    public void testNotFoundException() throws Exception {

        when(storeService.getStoreById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(StoreController.BASE_URL + "/222")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


}