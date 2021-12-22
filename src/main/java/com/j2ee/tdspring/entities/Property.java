package com.j2ee.tdspring.entities;

import com.j2ee.tdspring.enums.PropertyType;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "system_properties")
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
