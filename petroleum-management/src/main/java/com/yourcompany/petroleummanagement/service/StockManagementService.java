package com.yourcompany.petroleummanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

@Service
public class StockManagementService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void supplyStock(Long productId, double quantity) {
        String sql = "INSERT INTO stock_transactions (product_id, date_time, movement_type, quantity) VALUES (?, NOW(), 'Entry', ?)";
        jdbcTemplate.update(sql, productId, quantity);
    }

}
