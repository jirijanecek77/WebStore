package cz.renovahodonin.webstore.bootstrap;

import cz.renovahodonin.webstore.model.Store;
import cz.renovahodonin.webstore.model.StoreItem;
import cz.renovahodonin.webstore.model.UnitOfMeasure;
import cz.renovahodonin.webstore.repositories.StoreItemRepository;
import cz.renovahodonin.webstore.repositories.StoreRepository;
import cz.renovahodonin.webstore.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>
{
    private StoreRepository storeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private StoreItemRepository storeItemRepository;

    public DevBootstrap(StoreRepository storeRepository, UnitOfMeasureRepository unitOfMeasureRepository, StoreItemRepository storeItemRepository)
    {
        this.storeRepository = storeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.storeItemRepository = storeItemRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
    {
        storeItemRepository.deleteAll();
        storeRepository.deleteAll();
        unitOfMeasureRepository.deleteAll();

        unitOfMeasureRepository.saveAll(getUnitsOfMeasure());
        storeRepository.saveAll(getStores());
        storeItemRepository.saveAll(getStoreItems());
    }

    private List<UnitOfMeasure> getUnitsOfMeasure()
    {
        List<UnitOfMeasure> unitOfMeasures = new ArrayList<>(6);
        unitOfMeasures.add(new UnitOfMeasure("ks"));
        unitOfMeasures.add(new UnitOfMeasure("kg"));
        unitOfMeasures.add(new UnitOfMeasure("t"));
        unitOfMeasures.add(new UnitOfMeasure("bm"));
        unitOfMeasures.add(new UnitOfMeasure("m2"));
        unitOfMeasures.add(new UnitOfMeasure("m3"));

        return unitOfMeasures;
    }

    private List<Store> getStores()
    {
        List<Store> stores = new ArrayList<>(3);

        Store naradi = new Store("Nářadí");
        Store material = new Store("Materiál");
        Store pisek = new Store("Písek");

        stores.add(naradi);
        stores.add(material);
        stores.add(pisek);

        return stores;
    }

    private List<StoreItem> getStoreItems()
    {
        List<StoreItem> items = new ArrayList<>();

        Store store = storeRepository.findAll().iterator().next();
        StoreItem pisek = new StoreItem(
                store,
                "pisek",
                unitOfMeasureRepository.findAll().iterator().next(),
                100);

        StoreItem cement = new StoreItem(
                store,
                "cement",
                unitOfMeasureRepository.findAll().iterator().next(),
                50);
        items.add(pisek);
        items.add(cement);

        return items;
    }
}
