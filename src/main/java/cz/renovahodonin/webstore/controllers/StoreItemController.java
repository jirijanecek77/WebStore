package cz.renovahodonin.webstore.controllers;

import cz.renovahodonin.webstore.repositories.StoreItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StoreItemController
{
    private StoreItemRepository storeItemRepository;

    public StoreItemController(StoreItemRepository storeItemRepository)
    {
        this.storeItemRepository = storeItemRepository;
    }

    @RequestMapping("/store_items")
    public String getStoreItems(Model model)
    {

        model.addAttribute("store_items", storeItemRepository.findAll());

        return "store_items";
    }
}
