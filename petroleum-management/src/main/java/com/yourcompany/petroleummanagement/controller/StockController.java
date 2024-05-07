package com.yourcompany.petroleummanagement.controller;

import com.yourcompany.petroleummanagement.service.StockManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {
    @Autowired
    private StockManagementService stockService;

    @PostMapping("/supply")
    public String supplyStock(@RequestParam Long productId, @RequestParam double quantity) {
        stockService.supplyStock(productId, quantity);
        return "Stock successfully supplied";
    }
}
