package com.j2ee.tdspring.services;

import com.j2ee.tdspring.entities.Model;
import com.j2ee.tdspring.entities.Property;
import com.j2ee.tdspring.repositories.ModelRepository;
import com.j2ee.tdspring.repositories.PropertyRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ModelService {
    private final ModelRepository modelRepository;
    private final PropertyRepository propertyRepository;
    private final TableGeneratorService tableGeneratorService;

    public ModelService(ModelRepository modelRepository, PropertyRepository propertyRepository, TableGeneratorService tableGeneratorService) {
        this.modelRepository = modelRepository;
        this.propertyRepository = propertyRepository;
        this.tableGeneratorService = tableGeneratorService;
    }

    public Model create(Model model) {
        Model savedModel = modelRepository.save(model);
        tableGeneratorService.createTable(model);

        return savedModel;
    }

    public void delete(Integer modelId) {
        Model model = modelRepository.findById(modelId).orElse(null);

        modelRepository.delete(model);
        tableGeneratorService.dropTable(model);
    }

    public Model addProperties(Integer modelId, List<Property> properties) {
        Model model = modelRepository.findById(modelId).orElse(null);
        model.properties.addAll(properties);
        modelRepository.save(model);

        tableGeneratorService.addColumnsToTable(model, properties);

        return model;
    }

    public Model removeProperties(Integer modelId, List<Integer> propertyIds) {
        List<Property> properties = propertyRepository.findAllById(propertyIds);

        Model model = modelRepository.findById(modelId).orElse(null);
        model.properties.removeAll(properties);
        modelRepository.save(model);

        tableGeneratorService.removeColumnsToTable(model, properties);

        return model;
    }
}
