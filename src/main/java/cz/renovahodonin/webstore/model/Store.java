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

    @OneToMany(mappedBy = "store")
    private List<StoreItem> items = new ArrayList<>();

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
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Objects.equals(id, store.id) &&
                Objects.equals(name, store.name) &&
                Objects.equals(items, store.items);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, items);
    }

    @Override
    public String toString()
    {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", items=" + items +
                '}';
    }
}
