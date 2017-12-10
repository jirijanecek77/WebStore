package cz.renovahodonin.webstore.api.v1.services;


import cz.renovahodonin.webstore.api.v1.dto.StoreDto;
import cz.renovahodonin.webstore.api.v1.dto.StoreItemDto;

import java.util.List;

public interface StoreService
{
    StoreDto getStoreById(Long id);

    List<StoreDto> getAllStores();

    List<StoreItemDto> getAllStoreItemsByStore(Long storeId);

    StoreDto addStore(StoreDto storeDto);

    StoreDto saveStore(Long id, StoreDto storeDto);

    void deleteStore(Long id);
}
