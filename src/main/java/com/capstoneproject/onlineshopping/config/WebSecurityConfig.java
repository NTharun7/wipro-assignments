package com.capstoneproject.onlineshopping.config;

import com.capstoneproject.onlineshopping.security.AuthTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class WebSecurityConfig {

    private final AuthTokenFilter authTokenFilter;

    public WebSecurityConfig(AuthTokenFilter authTokenFilter) {
        this.authTokenFilter = authTokenFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> {})   // ✅ enable CORS
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Swagger (public)
                .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()

                // ✅ Auth endpoints
                .requestMatchers("/auth/**").permitAll()

                // ✅ Error endpoint
                .requestMatchers("/error").permitAll()

                // Reports (ADMIN only)
                .requestMatchers("/reports/**").hasRole("ADMIN")

                // Products — GET open; write = ADMIN
                .requestMatchers(HttpMethod.GET, "/products/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/products/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,  "/products/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/products/**").hasRole("ADMIN")

                // Categories — GET open; write = ADMIN
                .requestMatchers(HttpMethod.GET, "/categories/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/categories/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,  "/categories/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/categories/**").hasRole("ADMIN")

                // Orders — Admin can get all, customers get their own
                .requestMatchers(HttpMethod.GET, "/orders").hasRole("ADMIN")
                .requestMatchers("/orders/**").authenticated()

                // Cart & Payments — must be authenticated
                .requestMatchers("/cart/**", "/payments/**").authenticated()

                // Everything else
                .anyRequest().authenticated()
            );

        // ✅ Add JWT filter
        http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // ✅ Allow frontend (http://localhost:3000) to call backend
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:3000")); // React dev server
        config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
