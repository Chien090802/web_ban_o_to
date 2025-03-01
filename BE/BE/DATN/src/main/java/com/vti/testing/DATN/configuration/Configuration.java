package com.vti.testing.DATN.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public ModelMapper initModelMapper(){
        return new ModelMapper();
    }
    @Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
}
