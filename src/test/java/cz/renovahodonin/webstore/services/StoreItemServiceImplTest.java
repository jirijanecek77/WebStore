package cz.renovahodonin.webstore.services;

public class StoreItemServiceImplTest
{
//    private static final Long STORE_ID = 1L;
//    private static final Long STORE_ITEM_ID = 2L;
//    private static final Long UNIT_ID = 3L;
//
//    private StoreItemServiceImpl storeItemService;
//
//    @Mock
//    private StoreItemRepository storeItemRepository;
//    @Mock
//    private StoreRepository storeRepository;
//    @Mock
//    private UnitOfMeasureRepository unitOfMeasureRepository;
//
//    @Before
//    public void setUp() throws Exception
//    {
//        MockitoAnnotations.initMocks(this);
//
//        storeItemService = new StoreItemServiceImpl(storeRepository, storeItemRepository, unitOfMeasureRepository);
//    }
//
//    @Test
//    public void testFindById() throws Exception
//    {
//        StoreItem item = new StoreItem();
//        Optional<StoreItem> storeItemOptional = Optional.of(item);
//
//        when(storeItemRepository.findById(anyLong())).thenReturn(storeItemOptional);
//
//        StoreItem storeItemReturned = storeItemService.findById(STORE_ITEM_ID);
//
//        assertNotNull("Null store item returned", storeItemReturned);
//        verify(storeItemRepository, times(1)).findById(anyLong());
//        verify(storeItemRepository, never()).findAll();
//    }
//
//    @Test(expected = NotFoundException.class)
//    public void testFindByIdNotFound() throws Exception
//    {
//        when(storeItemRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        storeItemService.findById(STORE_ITEM_ID);
//    }
//
//    @Test
//    public void testDelete() throws Exception
//    {
//        storeItemService.delete(STORE_ITEM_ID);
//
//        //then
//        verify(storeItemRepository, times(1)).deleteById(anyLong());
//    }
//
//    @Test
//    public void testSave() throws Exception
//    {
//        //given
//        UnitOfMeasure unit = new UnitOfMeasure();
//        unit.setId(UNIT_ID);
//
//        StoreItem storeItem = new StoreItem();
//        storeItem.setId(STORE_ITEM_ID);
//        storeItem.setUnit(unit);
//        storeItem.setName("name");
//
//        Store store = new Store();
//        store.setId(STORE_ID);
//        store.addStoreItem(storeItem);
//
//        when(unitOfMeasureRepository.findById(UNIT_ID)).thenReturn(Optional.of(unit));
//        when(storeRepository.findById(STORE_ID)).thenReturn(Optional.of(store));
//        when(storeRepository.save(any())).thenReturn(store);
//
//        //when
//        StoreItem savedStoreItem = storeItemService.save(STORE_ID, storeItem);
//
//        //then
//        assertEquals(STORE_ITEM_ID, savedStoreItem.getId());
//        verify(storeRepository, times(1)).save(any(Store.class));
//    }

}