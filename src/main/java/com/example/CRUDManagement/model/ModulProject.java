package com.example.CRUDManagement.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="projects")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class ModulProject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String listProject;
    private  String company;
}
