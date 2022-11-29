package org.procode.management.repository;

import org.procode.management.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author arsen
 */
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {}
