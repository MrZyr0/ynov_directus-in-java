package com.j2ee.tdspring.entities;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "system_models")
@Getter
@Setter
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Integer id;

    @Column(unique = true)
    String name;

    @Column
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "model_id")
    public List<Property> properties;

    public void addProperties(List<Property> properties) {
        properties.addAll(properties);
    }

    public void removeProperties(List<Property> properties) {
        properties.removeAll(properties);
    }
}
