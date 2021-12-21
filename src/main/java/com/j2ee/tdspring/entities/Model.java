package com.j2ee.tdspring.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Integer id;

    @Column
    String name;

    @Column
    @OneToMany(cascade = CascadeType.ALL)
    public List<Property> properties;

    public void addProperties(List<Property> properties) {
        properties.addAll(properties);
    }

    public void removeProperties(List<Property> properties) {
        properties.removeAll(properties);
    }
}
