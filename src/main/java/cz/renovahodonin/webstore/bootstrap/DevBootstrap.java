package cz.renovahodonin.webstore.bootstrap;

import cz.renovahodonin.webstore.model.Receipt;
import cz.renovahodonin.webstore.model.Store;
import cz.renovahodonin.webstore.model.StoreItem;
import cz.renovahodonin.webstore.model.UnitOfMeasure;
import cz.renovahodonin.webstore.repositories.StoreRepository;
import cz.renovahodonin.webstore.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>
{
    private StoreRepository storeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public DevBootstrap(StoreRepository storeRepository, UnitOfMeasureRepository unitOfMeasureRepository)
    {
        this.storeRepository = storeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
    {
        storeRepository.deleteAll();
        unitOfMeasureRepository.deleteAll();

        unitOfMeasureRepository.saveAll(getUnitsOfMeasure());
        List<Store> stores = getStores();
        storeRepository.saveAll(stores);
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
        Store pisek = new Store("Elektro");

        material.setItems(getStoreItems());
        material.setReceipts(getReceipts());

        stores.add(material);
        stores.add(naradi);
        stores.add(pisek);

        return stores;
    }

    private List<Receipt> getReceipts()
    {
        List<Receipt> receipts = new ArrayList<>();

        Receipt receipt1 = new Receipt(LocalDate.of(2017,11,24), "P001");
        Receipt receipt2 = new Receipt(LocalDate.of(2017,11,25), "P002");
        Receipt receipt3 = new Receipt(LocalDate.of(2017,11,25), "P003");
        Receipt receipt4 = new Receipt(LocalDate.of(2017,11,26), "V001");

        receipts.add(receipt1);
        receipts.add(receipt2);
        receipts.add(receipt3);
        receipts.add(receipt4);

        return receipts;
    }

    private List<StoreItem> getStoreItems()
    {
        List<StoreItem> items = new ArrayList<>();

        StoreItem pisek = new StoreItem(
                "pisek",
                unitOfMeasureRepository.findAll().iterator().next(),
                100);

        StoreItem cement = new StoreItem(
                "cement",
                unitOfMeasureRepository.findAll().iterator().next(),
                50);
        items.add(pisek);
        items.add(cement);

        return items;
    }
}
