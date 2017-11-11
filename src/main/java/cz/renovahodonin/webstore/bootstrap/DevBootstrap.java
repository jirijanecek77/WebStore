package cz.renovahodonin.webstore.bootstrap;

import cz.renovahodonin.webstore.model.ItemUnit;
import cz.renovahodonin.webstore.model.Store;
import cz.renovahodonin.webstore.model.StoreItem;
import cz.renovahodonin.webstore.repositories.StoreItemRepository;
import cz.renovahodonin.webstore.repositories.StoreRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>
{
    private StoreRepository storeRepository;
    private StoreItemRepository storeItemRepository;

    public DevBootstrap(StoreRepository storeRepository, StoreItemRepository storeItemRepository)
    {
        this.storeRepository = storeRepository;
        this.storeItemRepository = storeItemRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
    {
        initData();
    }

    private void initData()
    {

        storeItemRepository.deleteAll();
        storeRepository.deleteAll();

        Store material = new Store("material");
        StoreItem pisek = new StoreItem(material, "pisek", ItemUnit.KG, 100);
        StoreItem cement = new StoreItem(material, "cement", ItemUnit.KG, 50);
        material.getItems().add(pisek);
        material.getItems().add(cement);

        storeRepository.save(material);
        storeItemRepository.save(pisek);
        storeItemRepository.save(cement);

        Store naradi = new Store("naradi");
        StoreItem vrtacka = new StoreItem(naradi, "vrtacka", ItemUnit.KS, 2);
        StoreItem michacka = new StoreItem(naradi, "michacka", ItemUnit.KS, 10);
        naradi.getItems().add(vrtacka);
        naradi.getItems().add(michacka);

        storeRepository.save(naradi);
        storeItemRepository.save(vrtacka);
        storeItemRepository.save(michacka);

    }
}
