package org.procode.management.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.procode.management.model.EmployeeEntity;
import org.procode.management.repository.EmployeeRepository;
import org.springframework.stereotype.Service;


/**
 * @author arsen
 */
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(EmployeeEntity employee) {
        employeeRepository.save(employee);
    }

    @Override
    public EmployeeEntity getEmployee(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
