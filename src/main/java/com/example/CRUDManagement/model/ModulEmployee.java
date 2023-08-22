package com.example.CRUDManagement.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="employees")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ModulEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String name;
    private String devisi;


}
