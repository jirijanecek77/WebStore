package cz.renovahodonin.webstore.controllers;

import cz.renovahodonin.webstore.services.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StoreController
{
    private StoreService storeService;

    public StoreController(StoreService storeService)
    {
        this.storeService = storeService;
    }

    @GetMapping({"/", "", "/index"})
    public String getView(Model model)
    {

        model.addAttribute("stores", storeService.getView());

        return "index";
    }

}
