package cz.renovahodonin.webstore.api.v1.dto;

import cz.renovahodonin.webstore.model.StoreItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreItemDto
{
    private String name;

    private UnitOfMeasureDto unit;
    private StoreDto store;

    public StoreItem fromDto() {
        StoreItem item = new StoreItem();
        item.setName(this.getName());
        item.setUnit(this.getUnit().fromDto());
        item.setStore(this.getStore().fromDto());
        return item;
    }

    public StoreItemDto fromStoreItem(StoreItem item){
        this.name = item.getName();
        this.store = new StoreDto().fromStore(item.getStore());
        this.unit = new UnitOfMeasureDto().fromUnitOfMeasure(item.getUnit());
        return this;
    }
}
