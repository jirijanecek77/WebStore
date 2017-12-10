package cz.renovahodonin.webstore.api.v1.services;

import cz.renovahodonin.webstore.api.v1.dto.StoreItemDto;

public interface StoreItemService
{
    StoreItemDto getStoreItemById(Long id);

    StoreItemDto addStoreItem(StoreItemDto storeItemDto);

    StoreItemDto saveStoreItem(Long Id, StoreItemDto storeItemDto);

    void deleteStoreItem(Long id);
}
