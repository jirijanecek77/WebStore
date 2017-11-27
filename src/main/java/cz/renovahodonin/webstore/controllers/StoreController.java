package cz.renovahodonin.webstore.controllers;

import cz.renovahodonin.webstore.constants.ServiceMapping;
import cz.renovahodonin.webstore.exceptions.NotFoundException;
import cz.renovahodonin.webstore.model.Store;
import cz.renovahodonin.webstore.services.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
class StoreController
{
    static final String STORE = "/index";
    static final String STORE_NEW = "/storeform";

    private StoreService storeService;

    StoreController(StoreService storeService)
    {
        this.storeService = storeService;
    }

    @GetMapping({"/index", "", ServiceMapping.STORE})
    String getView(Model model)
    {

        model.addAttribute("stores", storeService.getView());

        return STORE;
    }

    @GetMapping(ServiceMapping.STORE_NEW)
    public String add(Model model)
    {
        model.addAttribute("store", new Store());

        return STORE_NEW;
    }

    @GetMapping("/{id}" + ServiceMapping.UPDATE)
    public String update(@PathVariable String id, Model model)
    {
        model.addAttribute("store", storeService.findById(Long.valueOf(id)));
        return STORE_NEW;
    }

    @PostMapping(ServiceMapping.STORE_POST)
    public String saveOrUpdate(@ModelAttribute Store store)
    {
        storeService.save(store);
        return "redirect:" + ServiceMapping.STORE;
    }

    @GetMapping("/{id}" + ServiceMapping.DELETE)
    public String delete(@PathVariable String id)
    {
        storeService.delete(Long.valueOf(id));
        return "redirect:" + ServiceMapping.STORE;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName(ExceptionHandlingController.ERROR_PAGE);
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }
}
