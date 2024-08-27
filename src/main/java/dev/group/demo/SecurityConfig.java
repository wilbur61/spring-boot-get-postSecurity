package dev.group.demo;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import org.springframework.security.core.userdetails.User;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    private LoginDetailsService loginDetailsService;

    SecurityConfig(LoginDetailsService loginDetailsService){
        this.loginDetailsService = loginDetailsService;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/register").permitAll();
                    auth.anyRequest().authenticated();

                })
                .formLogin(withDefaults()
                ).build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(loginDetailsService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
//        auth.setUserDetailsService(loginDetailsService);
//        auth.setPasswordEncoder(passwordEncoder());
//        return auth;
//    }

    @Bean
    public static BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
//    @Bean
//    public static PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }

/*

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails wilbur = User.builder()
                .username("wilbur")
                .password("{noop}123")
//                .roles("SUPERUSER")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
//                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
//                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(wilbur, mary, susan);
    }

*/

}