package cz.renovahodonin.webstore.api.v1.services;

import cz.renovahodonin.webstore.api.v1.dto.StoreDto;
import cz.renovahodonin.webstore.api.v1.dto.StoreItemDto;
import cz.renovahodonin.webstore.exceptions.ResourceNotFoundException;
import cz.renovahodonin.webstore.model.Store;
import cz.renovahodonin.webstore.repositories.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
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
        Store store = storeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        return new StoreDto().fromStore(store);
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
    public List<StoreItemDto> getAllStoreItemsByStore(Long storeId)
    {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new ResourceNotFoundException(storeId));

        return store.getItems().stream()
                .map(item -> new StoreItemDto().fromStoreItem(item))
                .collect(Collectors.toList());
    }

    @Override
    public StoreDto addStore(StoreDto storeDto)
    {
        Store store = storeRepository.save(storeDto.fromDto());

        return new StoreDto().fromStore(store);
    }

    @Override
    public boolean validate(StoreDto storeDto, BindingResult result)
    {
        if (storeRepository.findAll().stream()
                .anyMatch(s -> s.getName().equals(storeDto.getName())))
        {
            result.rejectValue("name", "Duplicate.storeDto.name");
            return false;
        }
        return !result.hasErrors();
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
