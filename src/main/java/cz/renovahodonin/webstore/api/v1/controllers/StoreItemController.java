package cz.renovahodonin.webstore.api.v1.controllers;

import cz.renovahodonin.webstore.api.v1.dto.StoreItemDto;
import cz.renovahodonin.webstore.api.v1.services.StoreItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(StoreItemController.BASE_URL)
public class StoreItemController
{
    public static final String BASE_URL = "/api/v1/storeitems";

    private final StoreItemService storeItemService;

    public StoreItemController(StoreItemService storeItemService)
    {
        this.storeItemService = storeItemService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StoreItemDto getStoreItemById(@PathVariable Long id)
    {
        return storeItemService.getStoreItemById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StoreItemDto createNewStoreItem(@RequestBody StoreItemDto storeItemDto)
    {
        return storeItemService.addStoreItem(storeItemDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StoreItemDto updateStoreItem(@PathVariable Long id, @RequestBody StoreItemDto storeItemDto)
    {
        return storeItemService.saveStoreItem(id, storeItemDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StoreItemDto patchStoreItem(@PathVariable Long id, @RequestBody StoreItemDto storeItemDto)
    {
        return storeItemService.saveStoreItem(id, storeItemDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStoreItem(@PathVariable Long id)
    {
        storeItemService.deleteStoreItem(id);
    }
}
