package cz.renovahodonin.webstore.controllers;

import cz.renovahodonin.webstore.services.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StoresController
{
    private StoreService storeService;

    public StoresController(StoreService storeService)
    {
        this.storeService = storeService;
    }

    @RequestMapping({"/", "", "/index"})
    public String showStores(Model model)
    {

        model.addAttribute("stores", storeService.getStores());

        return "index";
    }

    @RequestMapping("/{id}/items")
    public String showStoreItems(@PathVariable(value = "id") String storeId, Model model){

        model.addAttribute("store_items", storeService.getStoreItems(Long.valueOf(storeId)));

        return "/items";
    }
}
