package org.procode.management.auth;

import java.util.Optional;

public interface ApplicationUserDao {
    Optional<ApplicationUser> selectUserFromDbByUserName(String username);
}
