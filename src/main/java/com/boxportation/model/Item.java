package com.boxportation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @NotBlank(message = "Item name cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9-_]+$", message = "allowed only letters, numbers, ‘-‘, ‘_’")
    private String name;


    @DecimalMin("0.1")
    private Double weight;

    @Column(unique = true)
    @NotBlank(message = "Item code cannot be blank")
    @Pattern(regexp = "^[A-Z0-9_]+$")
    private String code;

    @Enumerated(EnumType.STRING)
    private ItemState status;
}
