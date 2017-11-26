package cz.renovahodonin.webstore.controllers;

import cz.renovahodonin.webstore.constants.ServiceMapping;
import cz.renovahodonin.webstore.constants.ViewName;
import cz.renovahodonin.webstore.model.Store;
import cz.renovahodonin.webstore.model.StoreItem;
import cz.renovahodonin.webstore.model.UnitOfMeasure;
import cz.renovahodonin.webstore.services.StoreItemService;
import cz.renovahodonin.webstore.services.StoreService;
import cz.renovahodonin.webstore.services.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
class StoreItemController
{
    private StoreService storeService;

    private StoreItemService storeItemService;
    private UnitOfMeasureService unitOfMeasureService;

    StoreItemController(StoreService storeService, StoreItemService storeItemService, UnitOfMeasureService unitOfMeasureService)
    {
        this.storeService = storeService;
        this.storeItemService = storeItemService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("/{storeId}" + ServiceMapping.STOREITEM)
    public String getView(@PathVariable String storeId, Model model)
    {

        model.addAttribute("store", storeService.findById(Long.valueOf(storeId)));

        return ViewName.STOREITEM;
    }

    @GetMapping("/{storeId}" + ServiceMapping.STOREITEM_NEW)
    public String add(@PathVariable String storeId, Model model)
    {
        Store store = new Store();
        Long id = Long.valueOf(storeId);
        store.setId(id);

        StoreItem storeItem = new StoreItem();
        storeItem.setStore(store);
        storeItem.setUnit(new UnitOfMeasure());

        model.addAttribute("storeId", id);
        model.addAttribute("storeItem", storeItem);
        model.addAttribute("units",  unitOfMeasureService.getView());

        return ViewName.STOREITEM_NEW;
    }

    @GetMapping("/{storeId}" + ServiceMapping.STOREITEM_POST + "/{id}" + ServiceMapping.STOREITEM_UPDATE)
    public String update(@PathVariable String storeId, @PathVariable String id, Model model)
    {
        model.addAttribute("storeId", Long.valueOf(storeId));
        model.addAttribute("storeItem", storeItemService.findById(Long.valueOf(id)));
        model.addAttribute("units", unitOfMeasureService.getView());
        return ViewName.STOREITEM_NEW;
    }

    @PostMapping("/{storeId}" + ServiceMapping.STOREITEM_POST)
    public String saveOrUpdate(@PathVariable String storeId, @ModelAttribute StoreItem storeItem)
    {
        storeItemService.save(Long.valueOf(storeId), storeItem);
        return "redirect:/" + storeId + ServiceMapping.STOREITEM;
    }

    @GetMapping("/{storeId}" + ServiceMapping.STOREITEM_POST + "/{id}" + ServiceMapping.STOREITEM_DELETE)
    public String delete(@PathVariable String storeId, @PathVariable String id)
    {
        storeItemService.delete(Long.valueOf(id));
        return "redirect:/" + storeId + ServiceMapping.STOREITEM;
    }
}
