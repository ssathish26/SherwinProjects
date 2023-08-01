package com.example.SimplestCRUDExample.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="Cars")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String model;

    @Column
    private Integer yearmade;

}
