package cz.renovahodonin.webstore.controllers;

public class StoreItemControllerTest
{
//    private static final Long STORE_ID = 1L;
//    private static final Long STORE_ITEM_ID = 2L;
//
//    @Mock
//    private StoreService storeService;
//
//    @Mock
//    private StoreItemService storeItemService;
//
//    @Mock
//    private UnitOfMeasureService unitOfMeasureService;
//
//    @Mock
//    private StoreItemValidator storeItemValidator;
//
//    private StoreItemController controller;
//    private MockMvc mockMvc;
//
//    @Before
//    public void setUp() throws Exception
//    {
//        MockitoAnnotations.initMocks(this);
//
//        controller = new StoreItemController(storeService, storeItemService, unitOfMeasureService, storeItemValidator);
//        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//    }
//
//    @Test
//    public void testGetView() throws Exception
//    {
//
//        //given
//        Store store = new Store();
//        store.setId(STORE_ID);
//        when(storeService.findById(anyLong())).thenReturn(store);
//
//        //when
//        mockMvc.perform(get("/" + STORE_ID + ServiceMapping.STOREITEM))
//                .andExpect(status().isOk())
//                .andExpect(view().name(StoreItemController.LISTFORM_URL))
//                .andExpect(dto().attributeExists("store"));
//
//        //then
//        verify(storeService, times(1)).findById(anyLong());
//    }
//
//    @Test
//    public void testPostNewStoreItemForm() throws Exception
//    {
//        //given
//        StoreItem storeItem = new StoreItem();
//        storeItem.setId(2L);
//
//        //when
//        when(storeItemService.save(anyLong(), any())).thenReturn(storeItem);
//
//        //then
//        mockMvc.perform(post("/" + STORE_ID + "/storeItem/")
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("id", "")
//                .param("description", "some string")
//        )
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:/" + STORE_ID + "/items"));
//    }
//
//    @Test
//    public void testGetUpdateView() throws Exception
//    {
//        //given
//        StoreItem storeItem = new StoreItem();
//        storeItem.setId(2L);
//
//        //when
//        when(storeItemService.findById(anyLong())).thenReturn(storeItem);
//        when(unitOfMeasureService.getView()).thenReturn(new ArrayList<>());
//
//        mockMvc.perform(get("/" + STORE_ID + ServiceMapping.STOREITEM_POST + "/" + STORE_ITEM_ID + ServiceMapping.UPDATE))
//                .andExpect(status().isOk())
//                .andExpect(view().name(StoreItemController.EDITFORM_URL))
//                .andExpect(dto().attributeExists("storeId"))
//                .andExpect(dto().attributeExists("storeItem"))
//                .andExpect(dto().attributeExists("units"));
//
//        //then
//        verify(storeItemService, times(1)).findById(anyLong());
//        verify(unitOfMeasureService, times(1)).getView();
//    }
//
//    @Test
//    public void testGetNewStoreForm() throws Exception
//    {
//        //given
//        when(unitOfMeasureService.getView()).thenReturn(new ArrayList<>());
//
//        //when
//        mockMvc.perform(get("/" + STORE_ID + ServiceMapping.STOREITEM_NEW))
//                .andExpect(status().isOk())
//                .andExpect(view().name(StoreItemController.EDITFORM_URL))
//                .andExpect(dto().attributeExists("storeId"))
//                .andExpect(dto().attributeExists("storeItem"))
//                .andExpect(dto().attributeExists("units"));
//
//        //then
//        verify(unitOfMeasureService, times(1)).getView();
//    }
//
//    @Test
//    public void testDeleteAction() throws Exception
//    {
//        mockMvc.perform(get("/" + STORE_ID + ServiceMapping.STOREITEM_POST + "/" + STORE_ITEM_ID + ServiceMapping.DELETE))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:/" + STORE_ID + "/items"));
//
//        verify(storeItemService, times(1)).delete(anyLong());
//    }

}
