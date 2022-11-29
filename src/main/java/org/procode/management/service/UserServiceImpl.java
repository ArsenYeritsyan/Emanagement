package org.procode.management.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.procode.management.model.PermissionEntity;
import org.procode.management.model.RoleEntity;
import org.procode.management.model.UserEntity;
import org.procode.management.repository.RoleRepository;
import org.procode.management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserEntity user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<RoleEntity> roles = new HashSet<>();
        roles.add(roleRepository.findById(3).get());
        //Set ROLE_EMPLOYEE with its PERMISSIONS to user as default authorities.

        userEntity.setRoles(roles);
        userRepository.save(userEntity);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserEntity findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserEntity getApplicationUser(int id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findUserEntityByUsername(username);
        userEntity.orElseThrow(() ->
                new UsernameNotFoundException(String.format("Username %s not found", username)));

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (RoleEntity role : userEntity.get().getRoles()) {
            for (PermissionEntity permission : role.getPermissions()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(permission.getPermission()));
            }
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        }

        userEntity.get().setGrantedAuthorities(grantedAuthorities);
        return userEntity.get();
    }
}
