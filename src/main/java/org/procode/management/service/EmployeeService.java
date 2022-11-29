package org.procode.management.service;

import java.util.List;

import org.procode.management.model.EmployeeEntity;

/**
 * @author arsen
 */
public interface EmployeeService {
    public List<EmployeeEntity> getAllEmployees();
    public void saveEmployee(EmployeeEntity employee);
    public EmployeeEntity getEmployee(int id);
    public void deleteEmployee(int id);
}
