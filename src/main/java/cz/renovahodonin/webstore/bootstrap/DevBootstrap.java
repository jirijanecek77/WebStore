package cz.renovahodonin.webstore.bootstrap;

import cz.renovahodonin.webstore.model.Store;
import cz.renovahodonin.webstore.model.StoreItem;
import cz.renovahodonin.webstore.model.UnitOfMeasure;
import cz.renovahodonin.webstore.repositories.StoreRepository;
import cz.renovahodonin.webstore.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DevBootstrap implements CommandLineRunner
{
    private StoreRepository storeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public DevBootstrap(StoreRepository storeRepository, UnitOfMeasureRepository unitOfMeasureRepository)
    {
        this.storeRepository = storeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void run(String... args) throws Exception
    {
        if (unitOfMeasureRepository.count() == 0)
        {
            List<UnitOfMeasure> unitsOfMeasure = getUnitsOfMeasure();
            unitOfMeasureRepository.saveAll(unitsOfMeasure);
            System.out.println("Units of measure loaded: " + unitOfMeasureRepository.count());
        }

        if (storeRepository.count() == 0)
        {
            List<Store> stores = getStores(unitOfMeasureRepository.findAll());
            storeRepository.saveAll(stores);
            System.out.println("Stores loaded: " + storeRepository.count());
        }

    }

    private List<UnitOfMeasure> getUnitsOfMeasure()
    {
        List<UnitOfMeasure> unitOfMeasures = new ArrayList<>(6);
        unitOfMeasures.add(createUnitItem("ks"));
        unitOfMeasures.add(createUnitItem("kg"));
        unitOfMeasures.add(createUnitItem("t"));
        unitOfMeasures.add(createUnitItem("bm"));
        unitOfMeasures.add(createUnitItem("m2"));
        unitOfMeasures.add(createUnitItem("m3"));

        return unitOfMeasures;
    }

    private UnitOfMeasure createUnitItem(String name)
    {
        UnitOfMeasure unit = new UnitOfMeasure();
        unit.setName(name);
        return unit;
    }

    private List<Store> getStores(List<UnitOfMeasure> unitsOfMeasure)
    {
        List<Store> stores = new ArrayList<>(3);

        Store naradi = createStore("Nářadí");
        naradi.addStoreItem(createStoreItem("polozka db 1", unitsOfMeasure.get(0)));
        naradi.addStoreItem(createStoreItem("polozka db 2", unitsOfMeasure.get(1)));
        naradi.addStoreItem(createStoreItem("polozka db 3", unitsOfMeasure.get(2)));
        naradi.addStoreItem(createStoreItem("polozka db 4", unitsOfMeasure.get(2)));

        Store material = createStore("Materiál");
        material.addStoreItem(createStoreItem("polozka db 11", unitsOfMeasure.get(3)));
        material.addStoreItem(createStoreItem("polozka db 12", unitsOfMeasure.get(3)));
        material.addStoreItem(createStoreItem("polozka db 13", unitsOfMeasure.get(1)));
        material.addStoreItem(createStoreItem("polozka db 14", unitsOfMeasure.get(5)));

        Store pisek = createStore("Elektro");

        stores.add(material);
        stores.add(naradi);
        stores.add(pisek);

        return stores;
    }

    private Store createStore(String name)
    {
        Store store = new Store();
        store.setName(name);
        return store;
    }

    private StoreItem createStoreItem(String name, UnitOfMeasure unit)
    {
        StoreItem item = new StoreItem();
        item.setName(name);
        item.setUnit(unit);

        return item;
    }
}
