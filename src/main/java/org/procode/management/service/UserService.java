package org.procode.management.service;

import org.procode.management.model.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public void saveUser(UserEntity user);
    public UserEntity findByUsername(String username);
    public UserEntity findById(int id);
    public void deleteUser(int id);

    public UserEntity getApplicationUser(int id);
}
