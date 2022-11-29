//package org.procode.management.config;
//
//import org.omg.CORBA.PRIVATE_MEMBER;
//import org.procode.management.service.UserServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.thymeleaf.spring5.util.SpringValueFormatter;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    private static final String ADMIN = "ADMIN";
//    @Autowired
//    private UserServiceImpl userServiceImpl;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private CustomUserDetailsService userDetailsService;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userServiceImpl);
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth)
//        throws Exception {
//        auth .userDetailsService(userDetailsService)
//            .passwordEncoder(passwordEncoder);
//            //.inMemoryAuthentication()
//            //.withUser("Arsen").password(passwordEncoder.encode("arsPass")).roles(ADMIN)
//            //.and()
//            //.withUser("user2").password(passwordEncoder.encode("user2")).roles("USER")
//            //.and()
//            //.withUser("manager").password(passwordEncoder.encode("leader")).roles("TEAMLEAD")
//            //.and()
//            //.withUser("admin").password(passwordEncoder.encode("admin")).roles(ADMIN);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
////                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS).and()
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/employee", "/tasks/{id}/edit", "/employee/{id}/tasks").hasAnyRole("ADMIN, TEAMLEAD, EMPLOYEE")
//                .antMatchers("/**").hasRole("TEAMLEAD")
//                .antMatchers("/**", "/employee/admin", "/employee/admin/**").hasRole(ADMIN)
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login").permitAll()
//                .defaultSuccessUrl("/employee", true)
//                .and()
//                .logout()
//                .logoutSuccessUrl("/login")
//                .logoutUrl("/logout")
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .permitAll();
//    }
//}
