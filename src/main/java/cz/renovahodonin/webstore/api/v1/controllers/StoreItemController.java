package cz.renovahodonin.webstore.api.v1.controllers;

import cz.renovahodonin.webstore.api.v1.dto.StoreItemDto;
import cz.renovahodonin.webstore.api.v1.services.StoreItemService;
import cz.renovahodonin.webstore.exceptions.IllegalValueException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    public StoreItemDto createNewStoreItem(@Validated @RequestBody StoreItemDto storeItemDto, BindingResult result)
    {
        if (!storeItemService.validate(storeItemDto, result))
        {
            throw new IllegalValueException(result.getAllErrors());
        }
        return storeItemService.addStoreItem(storeItemDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StoreItemDto updateStoreItem(@Validated @PathVariable Long id, @RequestBody StoreItemDto storeItemDto, BindingResult result)
    {
        if (result.hasErrors())
        {
            throw new IllegalValueException(result.getAllErrors());
        }
        return storeItemService.saveStoreItem(id, storeItemDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StoreItemDto patchStoreItem(@Validated @PathVariable Long id, @RequestBody StoreItemDto storeItemDto, BindingResult result)
    {
        if (result.hasErrors())
        {
            throw new IllegalValueException(result.getAllErrors());
        }
        return storeItemService.saveStoreItem(id, storeItemDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStoreItem(@PathVariable Long id)
    {
        storeItemService.deleteStoreItem(id);
    }
}
