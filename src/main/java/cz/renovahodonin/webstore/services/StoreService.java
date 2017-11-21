package cz.renovahodonin.webstore.services;

import cz.renovahodonin.webstore.model.Store;

import java.util.List;

public interface StoreService
{
    List<Store> getView();

    Store findById(Long storeId);
}
