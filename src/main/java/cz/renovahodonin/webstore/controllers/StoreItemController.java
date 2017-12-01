package cz.renovahodonin.webstore.controllers;

import cz.renovahodonin.webstore.constants.ServiceMapping;
import cz.renovahodonin.webstore.model.Store;
import cz.renovahodonin.webstore.model.StoreItem;
import cz.renovahodonin.webstore.model.UnitOfMeasure;
import cz.renovahodonin.webstore.services.StoreItemService;
import cz.renovahodonin.webstore.services.StoreService;
import cz.renovahodonin.webstore.services.UnitOfMeasureService;
import cz.renovahodonin.webstore.validators.StoreItemValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
class StoreItemController
{
    static final String LISTFORM_URL = "/storeitem/storeitemlist";
    static final String EDITFORM_URL = "/storeitem/storeitemform";

    private StoreService storeService;
    private StoreItemService storeItemService;
    private UnitOfMeasureService unitOfMeasureService;

    private StoreItemValidator storeItemValidator;

    StoreItemController(StoreService storeService, StoreItemService storeItemService, UnitOfMeasureService unitOfMeasureService, StoreItemValidator storeItemValidator)
    {
        this.storeService = storeService;
        this.storeItemService = storeItemService;
        this.unitOfMeasureService = unitOfMeasureService;
        this.storeItemValidator = storeItemValidator;
    }

    @GetMapping("/{storeId}" + ServiceMapping.STOREITEM)
    public String getView(@PathVariable String storeId, Model model)
    {

        model.addAttribute("store", storeService.findById(Long.valueOf(storeId)));

        return LISTFORM_URL;
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

        return EDITFORM_URL;
    }

    @GetMapping("/{storeId}" + ServiceMapping.STOREITEM_POST + "/{id}" + ServiceMapping.UPDATE)
    public String update(@PathVariable String storeId, @PathVariable String id, Model model)
    {
        model.addAttribute("storeId", Long.valueOf(storeId));
        model.addAttribute("storeItem", storeItemService.findById(Long.valueOf(id)));
        return EDITFORM_URL;
    }

    @PostMapping("/{storeId}" + ServiceMapping.STOREITEM_POST)
    public String saveOrUpdate(@PathVariable String storeId, @ModelAttribute("storeItem") StoreItem storeItem, BindingResult bindingResult)
    {
        Store store = new Store();
        store.setId(Long.valueOf(storeId));
        storeItem.setStore(store);

        storeItemValidator.validate(storeItem, bindingResult);

        if (bindingResult.hasErrors()) {
            return EDITFORM_URL;
        }

	    storeItemService.save(Long.valueOf(storeId), storeItem);
	    return "redirect:/" + storeId + ServiceMapping.STOREITEM;
    }

    @GetMapping("/{storeId}" + ServiceMapping.STOREITEM_POST + "/{id}" + ServiceMapping.DELETE)
    public String delete(@PathVariable String storeId, @PathVariable String id)
    {
        storeItemService.delete(Long.valueOf(id));
        return "redirect:/" + storeId + ServiceMapping.STOREITEM;
    }

    @ModelAttribute("units")
    public List<UnitOfMeasure> registerUnitOfMeasure() {
        return unitOfMeasureService.getView();
    }
}
