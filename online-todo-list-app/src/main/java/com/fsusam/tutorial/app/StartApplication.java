package com.fsusam.tutorial.app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication( scanBasePackages ={"com.fsusam.tutorial"},exclude = { SecurityAutoConfiguration.class })
@EntityScan(basePackages = {"com.fsusam.tutorial.persist.domain"})
@EnableJpaRepositories(basePackages = {"com.fsusam.tutorial.persist.repository"})
public class StartApplication {

    public static void main(final String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() { return new ModelMapper(); }
}
