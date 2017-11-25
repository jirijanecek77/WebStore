package cz.renovahodonin.webstore.controllers;

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

    @GetMapping({"/", "", "/index"})
    String getView(Model model)
    {

        model.addAttribute("stores", storeService.getView());

        return "index";
    }

    @GetMapping("store/new")
    public String add(Model model)
    {
        model.addAttribute("store", new Store());

        return "/storeform";
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable String id, Model model)
    {
        model.addAttribute("store", storeService.findById(Long.valueOf(id)));
        return "/storeform";
    }

    @PostMapping("/store/")
    public String saveOrUpdate(@ModelAttribute Store store)
    {
        storeService.save(store);
        return "redirect:/";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable String id)
    {
        storeService.delete(Long.valueOf(id));
        return "redirect:/";
    }

}
