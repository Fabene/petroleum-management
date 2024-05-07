package com.yourcompany.petroleummanagement.controller;

import com.yourcompany.petroleummanagement.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class SaleController {
    @Autowired
    private SaleService saleService;

    @PostMapping("/sellProduct")
    public String sellProduct(@RequestParam Long productId, @RequestParam double quantity, @RequestParam(required = false, defaultValue = "0") double amount) {
        return saleService.sellProduct(productId, quantity, amount);
    }
}
