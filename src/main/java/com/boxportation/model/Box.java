package com.boxportation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Box {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Size(max=20, message = "txref cannot be more than 20 charaters")
    private String txref;

    @Transient
    private Double weightLimit = 500.00;

    @Range(min = 0, max = 100, message = "Battery capacity ranges between 0 and 100")
    private Double batteryCapacity;

    @Enumerated(EnumType.STRING)
    private BoxState state;

    @Range(max = 500, message = "weight cannot be more than 500gr")
    private double weightCarried;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "box_item",
    joinColumns = @JoinColumn(name = "box_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "itemId"))
    private List<Item> itemList;

}
