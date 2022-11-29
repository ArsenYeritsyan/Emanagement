package org.procode.management.repository;

import java.util.Optional;

import org.procode.management.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author arsen
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	public Optional<UserEntity> findUserEntityByUsername(String username);
	public UserEntity findByUsername(String username);
	public UserEntity findById(int id);

	UserEntity findByEmail(String email);
}
