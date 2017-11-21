package cz.renovahodonin.webstore.services;

import cz.renovahodonin.webstore.model.Store;
import cz.renovahodonin.webstore.repositories.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService
{
    private final StoreRepository storeRepository;

    public StoreServiceImpl(StoreRepository storeRepository)
    {
        this.storeRepository = storeRepository;
    }

    @Override
    public List<Store> getView()
    {
        List<Store> stores = new ArrayList<>();
        storeRepository.findAll().iterator().forEachRemaining(stores::add);
        return stores.stream().sorted(Comparator.comparing(Store::getName)).collect(Collectors.toList());
    }

    @Override
    public Store findById(Long storeId)
    {
        return storeRepository.findById(storeId).orElseThrow(() -> new RuntimeException("Sklad s ID " + storeId + " nebyl nalezen!"));
    }

}
