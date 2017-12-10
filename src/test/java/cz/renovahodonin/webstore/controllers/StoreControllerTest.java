package cz.renovahodonin.webstore.controllers;

import cz.renovahodonin.webstore.api.v1.controllers.StoreController;
import cz.renovahodonin.webstore.api.v1.dto.StoreDto;
import cz.renovahodonin.webstore.api.v1.services.StoreService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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

        mockMvc.perform(get(StoreController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(storeDto_1.getName())));
    }

    @Test
    public void createNewStore() throws Exception
    {

        given(storeService.addStore(storeDto_1)).willReturn(storeDto_1);

        mockMvc.perform(post(StoreController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.asJsonString(storeDto_1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(storeDto_1.getName())));
    }

    @Test
    public void updateStore() throws Exception
    {

        given(storeService.saveStore(anyLong(), any(StoreDto.class))).willReturn(storeDto_1);

        mockMvc.perform(put(StoreController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.asJsonString(storeDto_1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(storeDto_1.getName())));
    }

    @Test
    public void patchStore() throws Exception
    {
        given(storeService.saveStore(anyLong(), any(StoreDto.class))).willReturn(storeDto_1);

        mockMvc.perform(patch(StoreController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.asJsonString(storeDto_1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(storeDto_1.getName())));
    }

    @Test
    public void deleteStore() throws Exception
    {
        mockMvc.perform(delete(StoreController.BASE_URL + "/1"))
                .andExpect(status().isOk());

        then(storeService).should().deleteStore(anyLong());

    }
}