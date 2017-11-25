package cz.renovahodonin.webstore.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Store
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "store")
    @OrderBy("name")
    private List<StoreItem> items = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "store")
    @OrderBy("date, number")
    private List<Receipt> receipts = new ArrayList<>();

    public Store()
    {
    }

    public Store(String name)
    {
        this.name = name;
    }

    public Store(String name, List<StoreItem> items)
    {
        this.name = name;
        this.items = items;
    }

    public Store(String name, List<StoreItem> items, List<Receipt> receipts)
    {
        this.name = name;
        this.items = items;
        this.receipts = receipts;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<StoreItem> getItems()
    {
        return items;
    }

    public void setItems(List<StoreItem> items)
    {
        this.items = items;
        items.forEach(item -> item.setStore(this));
    }

    public void addStoreItem(StoreItem item)
    {
        item.setStore(this);
        this.items.add(item);
    }

    public List<Receipt> getReceipts()
    {
        return receipts;
    }

	public void setReceipts(List<Receipt> receipts)
	{
		this.receipts = receipts;
		receipts.forEach(receipt -> receipt.setStore(this));
	}

    public void addReceipt(Receipt receipt)
    {
        receipt.setStore(this);
        this.receipts.add(receipt);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Objects.equals(id, store.id) &&
                Objects.equals(name, store.name) &&
                Objects.equals(items, store.items) &&
                Objects.equals(receipts, store.receipts);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, items, receipts);
    }

    @Override
    public String toString()
    {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", items=" + items +
                ", receipts=" + receipts +
                '}';
    }
}
