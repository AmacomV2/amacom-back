package com.amacom.amacom.util.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.amacom.amacom.util.Jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

        private final JwtAuthenticationFilter jwtAuthenticationFilter;
        private final AuthenticationProvider authProvider;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                return http
                                .csrf(csrf -> csrf.disable())
                                .cors(Customizer.withDefaults())
                                .authorizeRequests(authRequest -> authRequest
                                                .antMatchers("/auth/**").permitAll()
                                                .antMatchers("/genders/getAll").permitAll()
                                                .antMatchers("/documentType/getAll").permitAll()
                                                .antMatchers("/civilStatus/getAll").permitAll()
                                                // .antMatchers("/person/create").permitAll()
                                                .antMatchers("/swagger-ui/**").permitAll()
                                                .antMatchers("/swagger-ui.html").permitAll()
                                                .antMatchers("/v2/**").permitAll()
                                                .antMatchers("/webjars/**", "/swagger-resources/**").permitAll()
                                                // .antMatchers("/user/**").hasRole("ROLE_USER")
                                                // .antMatchers("/admin/**").hasRole("ROLE_ADMIN")
                                                // .antMatchers("/moderador/**").hasRole("ROLE_MODERATOR")
                                                .anyRequest().authenticated())
                                .sessionManagement(sessionManager -> sessionManager
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authenticationProvider(authProvider)
                                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                                .httpBasic(Customizer.withDefaults())
                                .build();

        }

}
