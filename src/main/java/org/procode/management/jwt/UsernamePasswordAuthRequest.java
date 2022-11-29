package org.procode.management.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UsernamePasswordAuthRequest {

    private String username;
    private String password;
}
