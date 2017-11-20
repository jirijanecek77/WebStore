package cz.renovahodonin.webstore.services;

import cz.renovahodonin.webstore.model.Store;
import cz.renovahodonin.webstore.model.StoreItem;
import cz.renovahodonin.webstore.repositories.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService
{
    private final StoreRepository storeRepository;

    public StoreServiceImpl(StoreRepository storeRepository)
    {
        this.storeRepository = storeRepository;
    }

    @Override
    public List<Store> getStores()
    {
        List<Store> stores = new ArrayList<>();
        storeRepository.findAll().iterator().forEachRemaining(stores::add);
        return stores;
    }

    @Override
    public List<StoreItem> getStoreItems(Long storeId)
    {
        Optional<Store> store = storeRepository.findById(storeId);

        if (store.isPresent())
        {
            return store.get().getItems();
        }

        throw new RuntimeException("Sklad s ID " + storeId + " nebyl nalezen!");
    }
}
