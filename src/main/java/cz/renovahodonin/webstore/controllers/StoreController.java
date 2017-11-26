package cz.renovahodonin.webstore.controllers;

import cz.renovahodonin.webstore.constants.ServiceMapping;
import cz.renovahodonin.webstore.constants.ViewName;
import cz.renovahodonin.webstore.model.Store;
import cz.renovahodonin.webstore.services.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
class StoreController
{
    private StoreService storeService;

    StoreController(StoreService storeService)
    {
        this.storeService = storeService;
    }

    @GetMapping({"/index", "", ServiceMapping.STORE})
    String getView(Model model)
    {

        model.addAttribute("stores", storeService.getView());

        return ViewName.STORE;
    }

    @GetMapping(ServiceMapping.STORE_NEW)
    public String add(Model model)
    {
        model.addAttribute("store", new Store());

        return ViewName.STORE_NEW;
    }

    @GetMapping("/{id}" + ServiceMapping.STORE_UPDATE)
    public String update(@PathVariable String id, Model model)
    {
        model.addAttribute("store", storeService.findById(Long.valueOf(id)));
        return ViewName.STORE_NEW;
    }

    @PostMapping(ServiceMapping.STORE_POST)
    public String saveOrUpdate(@ModelAttribute Store store)
    {
        storeService.save(store);
        return "redirect:" + ServiceMapping.STORE;
    }

    @GetMapping("/{id}" + ServiceMapping.STORE_DELETE)
    public String delete(@PathVariable String id)
    {
        storeService.delete(Long.valueOf(id));
        return "redirect:" + ServiceMapping.STORE;
    }
}
