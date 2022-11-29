package org.procode.management.jwt;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;

import com.google.common.base.Strings;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

    private static final String BEARER_PREFIX = "Bearer ";
    private static final String KEY = "securesecuresecuresecuresecuresecuresecures";
    private static final String AUTHORITIES = "authorities";

    private static List<Map<String, String>> getAuthoritiesList(String token) {
        return (List<Map<String, String>>) Jwts.parser()
            .setSigningKey(Keys.hmacShaKeyFor(KEY.getBytes()))
            .parseClaimsJws(token)
            .getBody()
            .get(AUTHORITIES);
    }

    public String createToken(Authentication authentication) {
        return BEARER_PREFIX +  Jwts.builder()
            .setSubject(authentication.getName())
            .claim(AUTHORITIES, authentication.getAuthorities())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 600000))
            .signWith(Keys.hmacShaKeyFor(KEY.getBytes()))
            .compact();
    }

    public String createRefreshToken(Authentication authentication) {
        return BEARER_PREFIX +  Jwts.builder()
            .setSubject(authentication.getName())
            .claim(AUTHORITIES, authentication.getAuthorities())
            .setIssuedAt(new Date())
            .setExpiration(java.sql.Date.valueOf(LocalDateTime.now().toLocalDate().plusWeeks(1)))
            .signWith(Keys.hmacShaKeyFor(KEY.getBytes()))
            .compact();
    }

    public String getUsername(String token) {
        return Jwts.parser()
            .setSigningKey(Keys.hmacShaKeyFor(KEY.getBytes()))
            .parseClaimsJws(token)
            .getBody().getSubject();
    }

    public List<GrantedAuthority> getAuthorities(String token) {
        return getAuthoritiesList(token)
            .stream()
            .map(map -> new SimpleGrantedAuthority(map.get("authority")))
            .collect(Collectors.toList());

    }

    public String resolveToken(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (Strings.isNullOrEmpty(header) || !header.startsWith(BEARER_PREFIX)) {
            return null;
        }
        return header.replace(BEARER_PREFIX, "");
    }
}

