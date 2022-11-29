package org.procode.management.auth;

import static org.procode.management.config.ApplicationUserRole.EMPLOYEE;
import static org.procode.management.config.ApplicationUserRole.MANAGER;
import static org.procode.management.config.ApplicationUserRole.MASTER;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("fake")
public class FakeApplicationUserDao implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<ApplicationUser> selectUserFromDbByUserName(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }


    private List<ApplicationUser> getApplicationUsers() {
        return Lists.newArrayList(
                new ApplicationUser(
                        "employee",
                        passwordEncoder.encode("password"),
                        EMPLOYEE.getAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        "manager",
                        passwordEncoder.encode("password"),
                        MANAGER.getAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        "master",
                        passwordEncoder.encode("password"),
                        MASTER.getAuthorities(),
                        true,
                        true,
                        true,
                        true
                )
        );
    }

}
