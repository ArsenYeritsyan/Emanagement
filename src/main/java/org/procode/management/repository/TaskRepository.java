package org.procode.management.repository;

import org.procode.management.model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author arsen
 */
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {}
