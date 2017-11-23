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

	@GetMapping("/{id}/update")
	public String updateStore(@PathVariable String id, Model model)
	{
		model.addAttribute("store", storeService.findById(Long.valueOf(id)));
		return "/storeform";
	}

    @PostMapping("/store/")
    public String saveOrUpdate(@ModelAttribute Store store){
        storeService.saveStore(store);
        return "redirect:/";
    }

	@GetMapping("/{id}/delete")
	public String deleteStore(@PathVariable String id)
	{
		storeService.deleteStore(Long.valueOf(id));
		return "redirect:/";
	}

}
