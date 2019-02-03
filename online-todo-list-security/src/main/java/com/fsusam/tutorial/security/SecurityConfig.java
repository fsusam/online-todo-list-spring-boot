package com.fsusam.tutorial.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.session.SessionManagementFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    CorsFilter corsFilter() {
        final CorsFilter filter = new CorsFilter();
        return filter;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .addFilterBefore(corsFilter(), SessionManagementFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/signin").permitAll()
                .antMatchers("/api/**").authenticated()
                .antMatchers("/auth/check").authenticated()
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
        /*
         * http .httpBasic().disable() .csrf().disable() .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) .and()
         * .authorizeRequests() .antMatchers("/api/sayHello").permitAll() .and() .apply(new JwtConfigurer(jwtTokenProvider));
         */
        /*
         * http .httpBasic().disable() .csrf().disable() .addFilterBefore(corsFilter(), SessionManagementFilter.class)
         * .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) .and() .apply(new JwtConfigurer(jwtTokenProvider));
         */

        //http.authorizeRequests().antMatchers("/api/**").permitAll();
        //http.authorizeRequests().antMatchers("/auth/signin**").permitAll();

    }

}
