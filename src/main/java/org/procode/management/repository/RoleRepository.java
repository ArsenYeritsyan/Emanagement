package org.procode.management.repository;

import org.procode.management.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author arsen
 */
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findByRole(String admin);
}
