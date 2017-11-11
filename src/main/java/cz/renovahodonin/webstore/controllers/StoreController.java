package cz.renovahodonin.webstore.controllers;

import cz.renovahodonin.webstore.repositories.StoreRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StoreController
{
    private StoreRepository storeRepository;

    public StoreController(StoreRepository storeRepository)
    {
        this.storeRepository = storeRepository;
    }

    @RequestMapping("/")
    public String getStores(Model model)
    {

        model.addAttribute("stores", storeRepository.findAll());

        return "stores";
    }
}
