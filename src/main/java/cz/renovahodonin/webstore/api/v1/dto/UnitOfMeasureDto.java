package cz.renovahodonin.webstore.api.v1.dto;

import cz.renovahodonin.webstore.model.UnitOfMeasure;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitOfMeasureDto
{
    private String name;

    public UnitOfMeasure fromDto() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setName(this.getName());
        return unitOfMeasure;
    }

    public UnitOfMeasureDto fromUnitOfMeasure(UnitOfMeasure unitOfMeasure){
        this.name = unitOfMeasure.getName();
        return this;
    }
}
