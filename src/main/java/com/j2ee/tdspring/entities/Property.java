package com.j2ee.tdspring.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Integer id;

    @Column
    String name;

    @Column
    PropertyType type;
}


enum PropertyType {
    STRING, INTEGER, DOUBLE, DATE
}
