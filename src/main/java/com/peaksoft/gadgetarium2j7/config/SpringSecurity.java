package com.peaksoft.gadgetarium2j7.config;

import com.peaksoft.gadgetarium2j7.config.jwt.JwtFilter;
import com.peaksoft.gadgetarium2j7.service.UserDetailServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SpringSecurity {

    JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailServiceImpl();
    }

    @Bean
    public AuthenticationManager daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers(
                                    "/api/users/sign-up",
                                    "/api/auth/sign-in",
                    "/api/users/sign-up","/api/auth/sing-up", "/api/auth/sign-in","/products","/products/search","/orders/**").permitAll()

                            .requestMatchers("/api/products/add-product").hasAuthority("ADMIN")
                            .requestMatchers("/api/products/setDescription/{id}").hasAnyAuthority("ADMIN", "USER")
                            .requestMatchers("/api/products/setPriceAndQuantity/{id}").hasAuthority("ADMIN")
                            .requestMatchers("/api/products/{id}").hasAuthority("ADMIN")
                            .requestMatchers("/api/products/get-all").hasAnyAuthority("ADMIN", "USER")
                            .requestMatchers("/api/products/search-product/{id}").hasAnyAuthority("ADMIN", "USER")
                            .requestMatchers("/api/products/compare-product/{id}").hasAnyAuthority("ADMIN", "USER")
                            .requestMatchers("/api/products/search-product-by-filter").hasAnyAuthority("ADMIN", "USER")
                            .requestMatchers("/api/products/delete").hasAnyAuthority("ADMIN", "USER")
                            .requestMatchers("/api/products/get-all-products-by-category").hasAnyAuthority("ADMIN", "USER")

                            .anyRequest().authenticated();
                })
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
