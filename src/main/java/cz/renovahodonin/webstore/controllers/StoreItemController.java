package cz.renovahodonin.webstore.controllers;

import cz.renovahodonin.webstore.services.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class StoreItemController
{
	private StoreService storeService;

	public StoreItemController(StoreService storeService)
	{
		this.storeService = storeService;
	}

	@GetMapping("/{storeId}/items")
	public String getView(@PathVariable String storeId, Model model){

		model.addAttribute("store", storeService.findById(Long.valueOf(storeId)).getName());
		model.addAttribute("items", storeService.findById(Long.valueOf(storeId)).getItems());

		return "/items";
	}
}
