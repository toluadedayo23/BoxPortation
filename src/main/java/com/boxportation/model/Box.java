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


    @Size(max=20, message = "txref cannot be more than 20 charaters")
    private String txref;

    @Range(max = 500, message = "weight cannot be more than 500gr")
    private Double weightLimit;

    @Range(min = 0, max = 100, message = "Battery capacity ranges between 0 and 100")
    private Double batteryCapacity;

    @Enumerated(EnumType.STRING)
    private BoxState state;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "box_item",
    joinColumns = @JoinColumn(name = "box_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "itemId"))
    private List<Item> itemList;

}
