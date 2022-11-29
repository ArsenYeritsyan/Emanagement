package org.procode.management.repository;

import org.procode.management.model.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author arsen
 */
public interface PermissionRepository extends JpaRepository<PermissionEntity, Integer> {}
