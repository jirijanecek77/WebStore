package cz.renovahodonin.webstore.services;

import cz.renovahodonin.webstore.model.StoreItem;

public interface StoreItemService
{
    StoreItem findById(Long id);

    void delete(Long id);

    StoreItem save(Long storeId, StoreItem storeItem);
}
