package cz.renovahodonin.webstore.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class StoreItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure unit;

    @ManyToOne
    private Store store;

}
