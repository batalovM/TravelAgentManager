package org.example.travelagentmanager.repository;

import org.example.travelagentmanager.model.Employee;
import org.example.travelagentmanager.repository.interfaces.EmployeeRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author batal
 * @Date 10.10.2024
 */
@Repository
public class EmployeeRepository implements EmployeeRep {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final RowMapper<Employee> employeeRowMapper = new RowMapper<>() {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setFirstName(rs.getString("firstname"));
            employee.setLastName(rs.getString("lastname"));
            employee.setSurName(rs.getString("surname"));
            return employee;
        }
    };
    @Override
    public Employee findById(int id) {
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return List.of();
    }

    @Override
    public void save(Employee employee) {

    }

    @Override
    public void update(Employee employee) {

    }

    @Override
    public void deleteById(int id) {

    }
}
