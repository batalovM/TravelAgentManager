package org.example.travelagentmanager.repository;

import org.example.travelagentmanager.model.ExcursionProgram;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author batal
 * @Date 04.12.2024
 */
@Repository
public class ExcursionRepository {
    private final JdbcTemplate jdbcTemplate;

    public ExcursionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Получение всех экскурсий
    public List<ExcursionProgram> getAll() {
        String sql = "SELECT id, excursionProgramName FROM excursionprogram";
        return jdbcTemplate.query(sql, new ExcursionRowMapper());
    }

    // Получение экскурсии по ID
    public ExcursionProgram getById(Long id) {
        String sql = "SELECT id, excursionProgramName FROM excursionprogram WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new ExcursionRowMapper(), id);
    }

    // Создание новой экскурсии
    public int create(ExcursionProgram excursion) {
        String sql = "INSERT INTO excursionprogram (excursionProgramName) VALUES (?)";
        return jdbcTemplate.update(sql, excursion.getExcursionProgramName());
    }

    // Обновление экскурсии
    public int update(Long id, ExcursionProgram excursion) {
        String sql = "UPDATE excursionprogram SET excursionProgramName = ? WHERE id = ?";
        return jdbcTemplate.update(sql, excursion.getExcursionProgramName(), id);
    }

    // Удаление экскурсии
    public int delete(Long id) {
        String sql = "DELETE FROM excursionprogram WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // Маппер для преобразования результата в объект Excursion
    private static class ExcursionRowMapper implements RowMapper<ExcursionProgram> {
        @Override
        public ExcursionProgram mapRow(ResultSet rs, int rowNum) throws SQLException {
            ExcursionProgram excursion = new ExcursionProgram();
            excursion.setId(rs.getInt("id"));
            excursion.setExcursionProgramName(rs.getString("excursionProgramName"));
            return excursion;
        }
    }
}
