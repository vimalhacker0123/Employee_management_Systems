package com.example.task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http
            .authorizeHttpRequests(auth -> auth

                .requestMatchers(
                        "/css/**",
                        "/js/**",
                        "/h2-console/**",
                        "/login"
                ).permitAll()

                .anyRequest().authenticated()
            )


            .formLogin(form -> form

                .loginPage("/login")
                .defaultSuccessUrl("/employees/dashboard", true)
                .permitAll()
            )


            .logout(logout -> logout

                .logoutSuccessUrl("/login")
                .permitAll()
            );


        http.csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**")
        );


        http.headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin())
        );


        return http.build();
    }



    @Bean
    InMemoryUserDetailsManager users() {


        UserDetails admin = User.builder()

                .username("admin")
                .password("{noop}admin123")
                .roles("ADMIN")
                .build();


        return new InMemoryUserDetailsManager(admin);
    }

}