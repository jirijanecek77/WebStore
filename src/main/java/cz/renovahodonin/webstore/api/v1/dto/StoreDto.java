package cz.renovahodonin.webstore.api.v1.dto;

import cz.renovahodonin.webstore.model.Store;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto
{
    private String name;

    public Store fromDto() {
        Store store = new Store();
        store.setName(this.getName());
        return store;
    }

    public StoreDto fromStore(Store store){
        this.name = store.getName();
        return this;
    }
}
