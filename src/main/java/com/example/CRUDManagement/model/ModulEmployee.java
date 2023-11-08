package com.example.CRUDManagement.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name="employees")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@SuperBuilder
public class ModulEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private  String name;
    private String devisi;


}
