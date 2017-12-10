package cz.renovahodonin.webstore.api.v1.controllers;

import cz.renovahodonin.webstore.api.v1.dto.StoreDto;
import cz.renovahodonin.webstore.api.v1.services.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(StoreController.BASE_URL)
public class StoreController
{

    public static final String BASE_URL = "/api/v1/stores";

    private final StoreService storeService;

    public StoreController(StoreService storeService)
    {
        this.storeService = storeService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StoreDto> getStoreList()
    {
        return storeService.getAllStores();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StoreDto getStoreById(@PathVariable Long id)
    {
        return storeService.getStoreById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StoreDto createNewStore(@RequestBody StoreDto StoreDTO)
    {
        return storeService.addStore(StoreDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StoreDto updateStore(@PathVariable Long id, @RequestBody StoreDto StoreDTO)
    {
        return storeService.saveStore(id, StoreDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StoreDto patchStore(@PathVariable Long id, @RequestBody StoreDto StoreDTO)
    {
        return storeService.saveStore(id, StoreDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStore(@PathVariable Long id)
    {
        storeService.deleteStore(id);
    }
}
