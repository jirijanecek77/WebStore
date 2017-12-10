package cz.renovahodonin.webstore.services;

public class StoreServiceImplTest
{
//    private static final Long STORE_ID = 1L;
//    private StoreServiceImpl storeService;
//
//    @Mock
//    private StoreRepository storeRepository;
//
//    @Before
//    public void setUp() throws Exception
//    {
//        MockitoAnnotations.initMocks(this);
//
//        storeService = new StoreServiceImpl(storeRepository);
//    }
//
//    @Test
//    public void testGetView() throws Exception
//    {
//
//        Store store = new Store();
//        List<Store> storesData = new ArrayList<>();
//        storesData.add(store);
//
//        when(storeService.getView()).thenReturn(storesData);
//
//        List<Store> stores = storeService.getView();
//
//        assertEquals(stores.size(), 1);
//        verify(storeRepository, times(1)).findAll();
//    }
//
//    @Test
//    public void testFindById() throws Exception
//    {
//        Store store = new Store();
//        store.setId(STORE_ID);
//        Optional<Store> storeOptional = Optional.of(store);
//
//        when(storeRepository.findById(anyLong())).thenReturn(storeOptional);
//
//        Store storeReturned = storeService.findById(STORE_ID);
//
//        assertNotNull("Null store returned", storeReturned);
//        verify(storeRepository, times(1)).findById(anyLong());
//        verify(storeRepository, never()).findAll();
//    }
//
//    @Test(expected = NotFoundException.class)
//    public void testFindByIdNotFound() throws Exception
//    {
//        when(storeRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        storeService.findById(STORE_ID);
//    }
//
//    @Test
//    public void testSaveStore() throws Exception
//    {
//        //given
//        Store store = new Store();
//        store.setId(STORE_ID);
//
//        when(storeRepository.save(any())).thenReturn(store);
//
//        //when
//        Store savedStore = storeService.save(store);
//
//        //then
//        assertEquals(STORE_ID, savedStore.getId());
//        verify(storeRepository, times(1)).save(any(Store.class));
//
//    }
//
//    @Test
//    public void testDeleteStore() throws Exception
//    {
//        //when
//        storeService.delete(STORE_ID);
//
//        //then
//        verify(storeRepository, times(1)).deleteById(anyLong());
//    }

}