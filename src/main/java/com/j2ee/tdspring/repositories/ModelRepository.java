package com.j2ee.tdspring.repositories;

import com.j2ee.tdspring.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, Integer> { }
