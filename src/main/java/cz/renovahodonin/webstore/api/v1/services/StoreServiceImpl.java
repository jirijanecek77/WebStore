package cz.renovahodonin.webstore.api.v1.services;

import cz.renovahodonin.webstore.api.v1.dto.StoreDto;
import cz.renovahodonin.webstore.model.Store;
import cz.renovahodonin.webstore.repositories.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService
{
    StoreRepository storeRepository;

    public StoreServiceImpl(StoreRepository storeRepository)
    {
        this.storeRepository = storeRepository;
    }

    @Override
    public StoreDto getStoreById(Long id)
    {
        Optional<Store> store = storeRepository.findById(id);

        if (store.isPresent())
        {
            return new StoreDto().fromStore(store.get());
        }
        return null;
    }

    @Override
    public List<StoreDto> getAllStores()
    {
        List<Store> stores = storeRepository.findAll();
        return stores.stream()
                .map(store -> new StoreDto().fromStore(store))
                .collect(Collectors.toList());
    }

    @Override
    public StoreDto addStore(StoreDto storeDto)
    {
        Store store = storeRepository.save(storeDto.fromDto());

        return new StoreDto().fromStore(store);
    }

    @Override
    public StoreDto saveStore(Long id, StoreDto storeDto)
    {
        Store store = storeDto.fromDto();
        store.setId(id);

        return new StoreDto().fromStore(storeRepository.save(store));
    }

    @Override
    public void deleteStore(Long id)
    {
        storeRepository.deleteById(id);
    }
}
