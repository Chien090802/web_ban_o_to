package com.vti.testing.DATN.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.vti.testing.DATN.config.exception.AuthExceptionHandler;
import com.vti.testing.DATN.service.IAccountService;

@Configuration
@EnableWebSecurity
public class WebSecutiryConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private IAccountService accountService;
    
    @Autowired
    private AuthExceptionHandler authExceptionHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService).passwordEncoder(new BCryptPasswordEncoder());
        //12346-> $10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .cors()
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(authExceptionHandler)
        .accessDeniedHandler(authExceptionHandler)
        .and()
        .httpBasic()
        .and()
        .authorizeRequests()
            .antMatchers("/api/v1/auth/**").permitAll()
//            .anyRequest().authenticated()
//            .anyRequest().permitAll()
                .antMatchers("/api/v1/sign-in").authenticated()
            .and()
            .httpBasic()
            .and()
            .csrf().disable();
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
        configuration.applyPermitDefaultValues();
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
