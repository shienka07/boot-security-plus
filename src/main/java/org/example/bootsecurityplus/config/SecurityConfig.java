package org.example.bootsecurityplus.config;

import org.example.bootsecurityplus.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomUserDetailsService customUserDetailsService) throws Exception {
        http.
                authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/").permitAll() // '/'는 모두에게 허용이 되어 있다
                        .requestMatchers("/admin").hasRole("ADMIN") // ADMIN
                        .requestMatchers("/user").hasRole("USER") // USER
                        .anyRequest().authenticated()
                )
//                .formLogin(Customizer.withDefaults()) // 기본을 쓰자
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                ) // 기본을 쓰자
//                .logout(Customizer.withDefaults()); // 기본을 쓰자
                .logout(logout -> logout.permitAll())
                .userDetailsService(customUserDetailsService);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 본인들이 판단...
        // 이게 기본이 bcrypt임.
//        return new BCryptPasswordEncoder(); // 이게 확실히 Bcrypt임.
        // noop. MD5, SHA-1, SHA-256..., AES-256
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    //실직
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        UserDetails admin = User.withUsername("admin")
//                .password(passwordEncoder.encode("admin"))
//                .roles("ADMIN")
//                .build();
//        UserDetails user = User.withUsername("user")
//                .password(passwordEncoder.encode("user"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//    }
}
