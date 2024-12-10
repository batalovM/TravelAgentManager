package org.example.travelagentmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author batal
 * @Date 05.12.2024
 */
@RestController
@RequestMapping("/api/execute-sql")
@CrossOrigin
public class SQLController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping
    public List<Map<String, Object>> executeSQL(@RequestBody Map<String, String> request) {
        String sql = request.get("sql");
        try {
            return jdbcTemplate.queryForList(sql);

        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения SQL-запроса: " + e.getMessage());
        }
    }
}
