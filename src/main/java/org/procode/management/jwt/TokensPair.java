package org.procode.management.jwt;

import lombok.Data;

@Data
public class TokensPair {
    String accessToken;
    String refreshToken;
}
