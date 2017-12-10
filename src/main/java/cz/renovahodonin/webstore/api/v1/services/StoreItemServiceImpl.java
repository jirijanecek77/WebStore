package cz.renovahodonin.webstore.api.v1.services;

import cz.renovahodonin.webstore.api.v1.dto.StoreItemDto;
import cz.renovahodonin.webstore.exceptions.NotFoundException;
import cz.renovahodonin.webstore.model.Store;
import cz.renovahodonin.webstore.model.StoreItem;
import cz.renovahodonin.webstore.repositories.StoreItemRepository;
import cz.renovahodonin.webstore.repositories.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreItemServiceImpl implements StoreItemService
{
    private final StoreRepository storeRepository;
    private final StoreItemRepository storeItemRepository;

    public StoreItemServiceImpl(StoreRepository storeRepository, StoreItemRepository storeItemRepository)
    {
        this.storeRepository = storeRepository;
        this.storeItemRepository = storeItemRepository;
    }

    @Override
    public StoreItemDto getStoreItemById(Long id)
    {
        StoreItem storeItem = storeItemRepository.findById(id).orElseThrow(NotFoundException::new);
        return new StoreItemDto().fromStoreItem(storeItem);
    }

    @Override
    public List<StoreItemDto> getAllStoreItemsByStore(Long storeId)
    {
        Store store = storeRepository.findById(storeId).orElseThrow(NotFoundException::new);

        return store.getItems().stream()
                .map(item -> new StoreItemDto().fromStoreItem(item))
                .collect(Collectors.toList());
    }

    @Override
    public StoreItemDto addStoreItem(StoreItemDto storeItemDto)
    {
        StoreItem savedStoreItem = storeItemRepository.save(storeItemDto.fromDto());
        return new StoreItemDto().fromStoreItem(savedStoreItem);
    }

    @Override
    public StoreItemDto saveStoreItem(Long Id, StoreItemDto storeItemDto)
    {
        StoreItem storeItem = storeItemDto.fromDto();
        storeItem.setId(Id);
        StoreItem savedStoreItem = storeItemRepository.save(storeItem);
        return new StoreItemDto().fromStoreItem(savedStoreItem);
    }

    @Override
    public void deleteStoreItem(Long id)
    {
        storeItemRepository.deleteById(id);
    }
}
