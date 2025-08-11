package com.library.LibraryAPPSpringBoot.controllers.config;

import com.library.LibraryAPPSpringBoot.controllers.security.AuthProviderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthProviderImpl authProviderImpl;

    public SecurityConfig(AuthProviderImpl authProvider) {
        this.authProviderImpl = authProvider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // TODO enable csrf in production
                .authenticationProvider(authProviderImpl)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/css/**", "/images/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults()
                )
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }

}
