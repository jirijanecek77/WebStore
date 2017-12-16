package cz.renovahodonin.webstore.api.v1.services;

import cz.renovahodonin.webstore.api.v1.dto.StoreItemDto;
import cz.renovahodonin.webstore.exceptions.ResourceNotFoundException;
import cz.renovahodonin.webstore.model.StoreItem;
import cz.renovahodonin.webstore.repositories.StoreItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class StoreItemServiceImpl implements StoreItemService
{
    private final StoreItemRepository storeItemRepository;

    public StoreItemServiceImpl(StoreItemRepository storeItemRepository)
    {
        this.storeItemRepository = storeItemRepository;
    }

    @Override
    public StoreItemDto getStoreItemById(Long id)
    {
        StoreItem storeItem = storeItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return new StoreItemDto().fromStoreItem(storeItem);
    }

    @Override
    public StoreItemDto addStoreItem(StoreItemDto storeItemDto)
    {
        StoreItem savedStoreItem = storeItemRepository.save(storeItemDto.fromDto());
        return new StoreItemDto().fromStoreItem(savedStoreItem);
    }

    @Override
    public boolean validate(StoreItemDto storeItemDto, BindingResult result)
    {
        if (storeItemRepository.findByStore(storeItemDto.getStore().fromDto()).stream()
                .anyMatch(s -> s.getName().equals(storeItemDto.getName())))
        {
            result.rejectValue("name", "Duplicate.storeItemDto.name");
            return false;
        }
        return !result.hasErrors();
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
