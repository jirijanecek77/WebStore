package cz.renovahodonin.webstore.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
public class StoreItem
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure unit;
    private Integer amount;

    @ManyToOne
    private Store store;

    public StoreItem()
    {
    }

    public StoreItem(String name, UnitOfMeasure unit, int amount)
    {
        this.name = name;
        this.unit = unit;
        this.amount = amount;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Store getStore()
    {
        return store;
    }

    public void setStore(Store store)
    {
        this.store = store;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public UnitOfMeasure getUnit()
    {
        return unit;
    }

    public void setUnit(UnitOfMeasure unit)
    {
        this.unit = unit;
    }

    public Integer getAmount()
    {
        return amount;
    }

    public void setAmount(Integer amount)
    {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreItem storeItem = (StoreItem) o;
        return amount == storeItem.amount &&
                Objects.equals(id, storeItem.id) &&
                Objects.equals(name, storeItem.name) &&
                unit == storeItem.unit &&
                Objects.equals(store, storeItem.store);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, unit, amount, store);
    }

    @Override
    public String toString()
    {
        return "StoreItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unit=" + unit +
                ", amount=" + amount +
                ", store=" + store +
                '}';
    }
}
