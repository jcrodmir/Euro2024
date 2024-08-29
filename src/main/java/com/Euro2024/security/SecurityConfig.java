package com.Euro2024.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Value("${security.user.name}")
    private String username;
    @Value("${security.user.password}")
    private String pass;

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(pass))
                .roles("ADMIN")  // Este rol puede realizar operaciones de escritura
                .build();

        return new InMemoryUserDetailsManager(user);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCryptPasswordEncoder es recomendado para producción
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests( auth ->
                        auth.requestMatchers(HttpMethod.GET, "/**").permitAll() // Permitir GET a todos
                        .requestMatchers(HttpMethod.POST, "/**").hasRole("ADMIN") // Solo ADMIN para POST
                        .requestMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")  // Solo ADMIN para PUT
                        .requestMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN") // Solo ADMIN para DELETE
                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults()); // Usar autenticación básica

        return http.build();
    }

}