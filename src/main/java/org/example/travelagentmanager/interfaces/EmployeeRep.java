package org.example.travelagentmanager.interfaces;


import org.example.travelagentmanager.model.Employee;

import java.util.List;

/**
 * @author batal
 * @Date 06.11.2024
 */
public interface EmployeeRep {
    Employee findById(int id);
    List<Employee> findAll();
    void save(Employee employee);
    void update(Employee employee);
    void deleteById(int id);
}
