//package org.example.travelagentmanager.auth;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.SecurityFilterChain;
//
///**
// * @author batal
// * @Date 17.11.2024
// */
//
//@Configuration
//public class SecurityConfig {
//
//    private final UserDetailsService userDetailsService;
//
//    public SecurityConfig(UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/director/**").hasRole("director")
//                        .requestMatchers("/api/manager/**").hasRole("manager")
//                        .requestMatchers("/api/client/**").hasRole("client")
//                        .anyRequest().authenticated()
//                )
//                .formLogin()
//                .loginProcessingUrl("/api/login")
//                .successHandler((request, response, authentication) -> {
//                    response.setContentType("application/json");
//                    response.getWriter().write("{\"message\": \"Login successful\"}");
//                })
//                .failureHandler((request, response, exception) -> {
//                    response.setContentType("application/json");
//                    response.getWriter().write("{\"error\": \"Login failed\"}");
//                })
//                .and()
//                .logout()
//                .logoutUrl("/api/logout")
//                .logoutSuccessHandler((request, response, authentication) -> {
//                    response.setContentType("application/json");
//                    response.getWriter().write("{\"message\": \"Logout successful\"}");
//                })
//                .and()
//                .csrf().disable();
//        return http.build();
//    }
//}
//
