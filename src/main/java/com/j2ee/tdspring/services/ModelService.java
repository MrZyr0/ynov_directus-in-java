package com.j2ee.tdspring.services;

import com.j2ee.tdspring.entities.Model;
import com.j2ee.tdspring.entities.Property;
import com.j2ee.tdspring.repositories.ModelRepository;
import com.j2ee.tdspring.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ModelService {
    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    public Model create(Model model) {
        return modelRepository.save(model);
    }

    public Model addProperties(Integer modelId, List<Property> properties) {
        Model model = modelRepository.findById(modelId).orElse(null);
        model.properties.addAll(properties);

        return modelRepository.save(model);
    }

    public Model removeProperties(Integer modelId, List<Integer> propertyIds) {
        List<Property> properties = propertyRepository.findAllById(propertyIds);

        Model model = modelRepository.findById(modelId).orElse(null);
        model.properties.removeAll(properties);

        return modelRepository.save(model);
    }
}
