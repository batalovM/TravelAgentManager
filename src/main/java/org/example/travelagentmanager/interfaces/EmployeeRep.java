package org.example.travelagentmanager.interfaces;


import org.example.travelagentmanager.model.Employee;

import java.util.List;
import java.util.Optional;

/**
 * @author batal
 * @Date 06.11.2024
 */
public interface EmployeeRep {
    Optional<Employee> findById(int id);
    List<Employee> findAll();
    void save(Employee employee);
    void update(Employee employee, int id);
    void deleteById(int id);
}
