package com.yourcompany.petroleummanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;

public class SaleService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public String sellProduct(Long productId, double quantity, double amount) {
        // Check available stock
        String stockQuery = "SELECT SUM(quantity) AS total FROM stock_transactions WHERE product_id = ?";
        Double availableQuantity = jdbcTemplate.queryForObject(stockQuery, new Object[]{productId}, Double.class);
        availableQuantity = availableQuantity != null ? availableQuantity : 0;

        if (availableQuantity < quantity) {
            return "Insufficient stock";
        }

        if (quantity > 200) {
            return "Requested quantity exceeds the limit of 200 liters per sale";
        }

        // Calculate quantity based on amount if provided
        if (amount != 0) {
            String priceQuery = "SELECT price FROM products WHERE id = ?";
            Double price = jdbcTemplate.queryForObject(priceQuery, new Object[]{productId}, Double.class);
            if (price != null) {
                quantity = amount / price;
            }
        }

        // Update stock (exit)
        String updateStockSql = "INSERT INTO stock_transactions (product_id, quantity, transaction_type, transaction_date) VALUES (?, ?, 'EXIT', ?)";
        jdbcTemplate.update(updateStockSql, productId, -quantity, new Timestamp(System.currentTimeMillis()));

        return "Sale successful. Quantity sold: " + quantity + " liters";
    }
}
