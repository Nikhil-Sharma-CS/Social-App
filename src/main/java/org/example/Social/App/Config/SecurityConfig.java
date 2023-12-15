package org.example.Social.App.Config;

import org.example.Social.App.Security.JwtAuthenticationEntryPoint;
import org.example.Social.App.Security.JwtAuthenticationFilter;
import org.example.Social.App.Service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint point;

    @Autowired
    private JwtAuthenticationFilter filter;

    @Autowired
    private CustomUserDetailService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    //This is the filter chain for security
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(AbstractHttpConfigurer::disable).cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/create").permitAll()
                        .requestMatchers("/auth/login").permitAll()
                .anyRequest().authenticated())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider()
    {
       DaoAuthenticationProvider daoAuthenticationProvider= new DaoAuthenticationProvider();
       daoAuthenticationProvider.setUserDetailsService(userDetailsService);
       daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);

       return daoAuthenticationProvider;
    }
}
