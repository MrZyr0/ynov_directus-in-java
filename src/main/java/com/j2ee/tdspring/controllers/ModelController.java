package com.j2ee.tdspring.controllers;

import com.j2ee.tdspring.entities.Model;
import com.j2ee.tdspring.entities.Property;
import com.j2ee.tdspring.services.ModelService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ModelController {
    @Autowired
    private ModelService modelService;

    @Operation(summary = "Créer un nouveau model")
    @PutMapping(path = "/models/")
    public Model createModel(@RequestBody Model model) {
        return modelService.create(model);
    }

    @Operation(summary = "Add properties to model")
    @PatchMapping(path = "/models/{modelId}")
    public Model addPropertiesToModel(@PathVariable(value = "modelId") Integer modelId, @RequestBody List<Property> properties) {
        return modelService.addProperties(modelId, properties);
    }

    @Operation(summary = "Remove properties to model")
    @DeleteMapping(path = "/models/{modelId}")
    public Model removePropertiesToModel(@PathVariable(value = "modelId") Integer modelId, @RequestBody List<Integer> propertyIds) {
        return modelService.removeProperties(modelId, propertyIds);
    }
}
