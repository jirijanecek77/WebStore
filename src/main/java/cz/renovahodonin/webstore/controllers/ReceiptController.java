package cz.renovahodonin.webstore.controllers;

import cz.renovahodonin.webstore.services.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ReceiptController
{
	private StoreService storeService;

	public ReceiptController(StoreService storeService)
	{
		this.storeService = storeService;
	}


	@GetMapping("/{storeId}/receipts")
	public String getView(@PathVariable String storeId, Model model){

		model.addAttribute("store", storeService.findById(Long.valueOf(storeId)).getName());
		model.addAttribute("receipts", storeService.findById(Long.valueOf(storeId)).getReceipts());

		return "/receipts";
	}
}
