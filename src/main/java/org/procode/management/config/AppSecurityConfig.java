package org.procode.management.config;

import static org.procode.management.config.ApplicationUserRole.EMPLOYEE;
import static org.procode.management.config.ApplicationUserRole.MANAGER;
import static org.procode.management.config.ApplicationUserRole.MASTER;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.procode.management.auth.ApplicationUserService;
import org.procode.management.jwt.JwtProvider;
import org.procode.management.jwt.JwtTokenVerifierFilter;
import org.procode.management.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author arsen
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;
    private final JwtProvider jwtProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtProvider,new ObjectMapper()))
                .addFilterAfter(new JwtTokenVerifierFilter(jwtProvider), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/", "index").permitAll()
                            .antMatchers("/employee", "/tasks/{id}/edit", "/employee/{id}/tasks")
            .hasAuthority(ApplicationUserPermission.TASK_READ.getPermission())
                            .antMatchers("/tasks/**","/employee/**").hasRole(MANAGER.name())
                            .antMatchers("/**", "/employee/admin", "/employee/admin/**").hasAuthority(MASTER.name())
                .anyRequest()
                .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
        //auth.userDetailsService(userDetailsService());

    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails userDetails = User.builder()
                .username("employee")
                .password(passwordEncoder.encode("password"))
                .authorities(EMPLOYEE.getAuthorities())
                .build();

        UserDetails manager = User.builder()
                .username("manager")
                .password(passwordEncoder.encode("password"))
                .authorities(MANAGER.getAuthorities())
                .build();

        UserDetails master = User.builder()
                .username("master")
                .password(passwordEncoder.encode("password"))
                .authorities(MASTER.getAuthorities())
                .build();
        return new InMemoryUserDetailsManager(userDetails, manager, master);
    }
}
