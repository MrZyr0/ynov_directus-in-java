package com.j2ee.tdspring.controllers;

import com.j2ee.tdspring.entities.Model;
import com.j2ee.tdspring.services.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Operation(summary = "List availables models")
    @GetMapping(path = "/items/")
    public List<Model> createModel() {
        return itemService.listItems();
    }
}
