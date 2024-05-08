package com.yourcompany.petroleummanagement.controller;

import com.yourcompany.petroleummanagement.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
@RestController
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/inventory")
    public Map<String, Object> getProductStock(@RequestParam Long productId) {
        return inventoryService.getProductStock(productId);
    }

    @GetMapping("/inventory/all")
    public List<Map<String, Object>> getAllProductStock() {
        return inventoryService.getAllProductStock();
    }
}
