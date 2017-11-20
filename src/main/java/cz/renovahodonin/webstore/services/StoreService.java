package cz.renovahodonin.webstore.services;

import cz.renovahodonin.webstore.model.Store;
import cz.renovahodonin.webstore.model.StoreItem;

import java.util.List;

public interface StoreService
{
    List<Store> getStores();

    List<StoreItem> getStoreItems(Long storeId);
}
