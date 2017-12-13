package cz.renovahodonin.webstore.controllers;

import cz.renovahodonin.webstore.api.v1.controllers.StoreItemController;
import cz.renovahodonin.webstore.api.v1.dto.StoreDto;
import cz.renovahodonin.webstore.api.v1.dto.StoreItemDto;
import cz.renovahodonin.webstore.api.v1.dto.UnitOfMeasureDto;
import cz.renovahodonin.webstore.api.v1.services.StoreItemService;
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

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {StoreItemController.class})
public class StoreItemControllerTest
{

    @MockBean
    private StoreItemService storeItemService;

    @Autowired
    private MockMvc mockMvc;

    private StoreItemDto storeItemDto;

    @Before
    public void setUp()
    {
        storeItemDto = new StoreItemDto("StoreItem", new UnitOfMeasureDto(), new StoreDto());
    }

    @Test
    public void getStoreItemById() throws Exception
    {

        given(storeItemService.getStoreItemById(anyLong())).willReturn(storeItemDto);

        mockMvc.perform(get(StoreItemController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(storeItemDto.getName())));
    }

    @Test
    public void createNewStoreItem() throws Exception
    {

        given(storeItemService.addStoreItem(storeItemDto)).willReturn(storeItemDto);

        mockMvc.perform(post(StoreItemController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.asJsonString(storeItemDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(storeItemDto.getName())));
    }

    @Test
    public void updateStore() throws Exception
    {

        given(storeItemService.saveStoreItem(anyLong(), any(StoreItemDto.class))).willReturn(storeItemDto);

        mockMvc.perform(put(StoreItemController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.asJsonString(storeItemDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(storeItemDto.getName())));
    }

    @Test
    public void patchStore() throws Exception
    {
        given(storeItemService.saveStoreItem(anyLong(), any(StoreItemDto.class))).willReturn(storeItemDto);

        mockMvc.perform(patch(StoreItemController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.asJsonString(storeItemDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(storeItemDto.getName())));
    }

    @Test
    public void deleteStoreItem() throws Exception
    {
        mockMvc.perform(delete(StoreItemController.BASE_URL + "/1"))
                .andExpect(status().isOk());

        then(storeItemService).should().deleteStoreItem(anyLong());

    }

    @Test
    public void testNotFoundException() throws Exception {

        when(storeItemService.getStoreItemById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(StoreItemController.BASE_URL + "/222")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
