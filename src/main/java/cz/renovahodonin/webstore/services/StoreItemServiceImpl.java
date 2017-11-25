package cz.renovahodonin.webstore.services;

import cz.renovahodonin.webstore.model.Store;
import cz.renovahodonin.webstore.model.StoreItem;
import cz.renovahodonin.webstore.repositories.StoreItemRepository;
import cz.renovahodonin.webstore.repositories.StoreRepository;
import cz.renovahodonin.webstore.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
class StoreItemServiceImpl implements StoreItemService
{
    private StoreRepository storeRepository;
    private StoreItemRepository storeItemRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    StoreItemServiceImpl(StoreRepository storeRepository, StoreItemRepository storeItemRepository, UnitOfMeasureRepository unitOfMeasureRepository)
    {
        this.storeRepository = storeRepository;
        this.storeItemRepository = storeItemRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public StoreItem findById(Long id)
    {
        return storeItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Položka skladu s ID " + id + " nebyla nalezena!"));
    }

    @Override
    @Transactional
    public StoreItem save(Long storeId, StoreItem storeItem)
    {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new RuntimeException("Sklad s ID " + storeId + " nebyl nalezen!"));

        if (store.getItems().stream()
                .anyMatch(item -> !item.getId().equals(storeItem.getId()) && item.getName().equals(storeItem.getName())))
        {
            throw new RuntimeException("Položka s názvem " + storeItem.getName() + " již existuje!");
        }

        Optional<StoreItem> storeItemInStore = store.getItems().stream()
                .filter(item -> item.getId().equals(storeItem.getId()))
                .findFirst();

        if (storeItemInStore.isPresent())
        {
            //update storeItem
            StoreItem updatedStoreItem = storeItemInStore.get();
            updatedStoreItem.setName(storeItem.getName());
            updatedStoreItem.setUnit(unitOfMeasureRepository
                    .findById(storeItem.getUnit().getId())
                    .orElseThrow(() -> new RuntimeException("Jednotka nebyla nalezena!")));
        }
        else
        {
            //add new storeItem
            storeItem.setAmount(0);
            store.addStoreItem(storeItem);
        }

        //save store and its new item
        Store savedStore = storeRepository.save(store);

        // find saved item for return
        return savedStore.getItems().stream()
                .filter(item -> item.getId().equals(storeItem.getId()))
                .findFirst()
                .orElseGet(savedStore.getItems().stream()
                        .filter(item -> item.getName().equals(storeItem.getName()))
                        .findFirst()::get
                );

    }

    @Override
    @Transactional
    public void delete(Long id)
    {
        storeItemRepository.deleteById(id);
    }


}
