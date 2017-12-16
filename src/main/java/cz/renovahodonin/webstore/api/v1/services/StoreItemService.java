package cz.renovahodonin.webstore.api.v1.services;

import cz.renovahodonin.webstore.api.v1.dto.StoreItemDto;
import org.springframework.validation.BindingResult;

public interface StoreItemService
{
    StoreItemDto getStoreItemById(Long id);

    StoreItemDto addStoreItem(StoreItemDto storeItemDto);

    boolean validate(StoreItemDto storeItemDto, BindingResult result);

    StoreItemDto saveStoreItem(Long Id, StoreItemDto storeItemDto);

    void deleteStoreItem(Long id);
}
