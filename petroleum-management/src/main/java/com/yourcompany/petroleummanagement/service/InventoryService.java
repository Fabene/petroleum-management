package com.yourcompany.petroleummanagement.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InventoryService {
    private JdbcTemplate jdbcTemplate;

    // Get stock status of a specific product
    public Map<String, Object> getProductStock(Long productId) {
        String stockQuery = "SELECT product_id, SUM(quantity) AS total FROM stock_transactions WHERE product_id = ? GROUP BY product_id";
        return jdbcTemplate.queryForMap(stockQuery, productId);
    }

    // Get stock status for all products
    public List<Map<String, Object>> getAllProductStock() {
        String stockQuery = "SELECT product_id, SUM(quantity) AS total FROM stock_transactions GROUP BY product_id";
        return jdbcTemplate.queryForList(stockQuery);
    }
}
