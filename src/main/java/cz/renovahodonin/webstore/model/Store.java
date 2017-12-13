package cz.renovahodonin.webstore.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Store
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date updated;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "store")
    @OrderBy("name")
    private List<StoreItem> items = new ArrayList<>();


//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "store")
//    @OrderBy("date, number")
//    private List<Receipt> receipts = new ArrayList<>();

    public void addStoreItem(StoreItem item)
    {
        item.setStore(this);
        this.items.add(item);
    }

}
