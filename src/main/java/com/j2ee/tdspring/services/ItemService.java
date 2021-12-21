package com.j2ee.tdspring.services;

import com.j2ee.tdspring.entities.Model;
import com.j2ee.tdspring.entities.Property;
import com.j2ee.tdspring.repositories.ModelRepository;
import com.j2ee.tdspring.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ModelRepository modelRepository;

    public List<Model> listItems() {
        return modelRepository.findAll();
    }
}
