package com.xk.common.config;

import com.xk.common.filter.CustomAuthenticationEntryPointFilter;
import com.xk.common.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final CustomAuthenticationEntryPointFilter customAuthenticationEntryPointFilter;

    private static String[] PERMIT_ALL = {
            "/v3/**",
            "/swagger-ui/**",
            "/login/**",
            "/api/**"
    };

    @Bean
    public SecurityFilterChain initSecurity(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // CSRF攻擊
                .authorizeHttpRequests( // 認證請求
                        auth -> {
                            auth.requestMatchers(PERMIT_ALL).permitAll();
                            auth.anyRequest().authenticated();
                        })
                .exceptionHandling( // 錯誤處理
                        httpSecurityExceptionHandlingConfigurer -> {
                            httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(
                                    customAuthenticationEntryPointFilter);
                        })
                .sessionManagement(
                        httpSecuritySessionManagementConfigurer ->
                                httpSecuritySessionManagementConfigurer.sessionCreationPolicy(
                                        SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
