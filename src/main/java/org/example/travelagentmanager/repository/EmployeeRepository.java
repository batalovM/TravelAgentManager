package org.example.travelagentmanager.repository;

import org.example.travelagentmanager.model.Employee;
import org.example.travelagentmanager.interfaces.EmployeeRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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
    private static final RowMapper<Employee> employeeRowMapper = (rs, rowNum) -> {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setFirstName(rs.getString("firstname"));
        employee.setLastName(rs.getString("lastname"));
        employee.setSurName(rs.getString("surname"));
        return employee;
    };
    @Override
    public Optional<Employee> findById(int id) {
        String sql = "select * from employee where id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, employeeRowMapper, id));
    }

    @Override
    public List<Employee> findAll() {
        String sql = "select * from employee";
        return jdbcTemplate.query(sql, employeeRowMapper);
    }

    @Override
    public void save(Employee employee) {
        String sql = "insert into employee (firstname, lastname, surName) values (?, ?, ?)";
        jdbcTemplate.update(sql, employee.getFirstName(), employee.getLastName(), employee.getSurName());
    }

    @Override
    public void update(Employee employee, int id) {
        String sql = "update employee set firstname = ? , lastname = ? , surname = ? where id = ?";
        jdbcTemplate.update(sql, employee.getFirstName(), employee.getLastName(), employee.getSurName(), employee.getId());
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from employee where id = ?";
        jdbcTemplate.update(sql, id);
    }
}
