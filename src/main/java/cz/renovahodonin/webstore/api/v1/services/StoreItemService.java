package cz.renovahodonin.webstore.api.v1.services;

import cz.renovahodonin.webstore.api.v1.dto.StoreItemDto;

import java.util.List;

public interface StoreItemService
{
    StoreItemDto getStoreItemById(Long id);

    List<StoreItemDto> getAllStoreItemsByStore(Long storeId);

    StoreItemDto addStoreItem(StoreItemDto storeItemDto);

    StoreItemDto saveStoreItem(Long Id, StoreItemDto storeItemDto);

    void deleteStoreItem(Long id);
}