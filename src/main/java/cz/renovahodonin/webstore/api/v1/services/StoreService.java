package cz.renovahodonin.webstore.api.v1.services;


import cz.renovahodonin.webstore.api.v1.dto.StoreDto;

import java.util.List;

public interface StoreService
{
    StoreDto getStoreById(Long id);

    List<StoreDto> getAllStores();

    StoreDto addStore(StoreDto StoreDTO);

    StoreDto saveStore(Long id, StoreDto StoreDTO);

    void deleteStore(Long id);
}
