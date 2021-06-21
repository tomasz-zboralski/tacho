package com.crud.tacho.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/v1/assignments/**").hasRole("DRIVER")
                .antMatchers("/v1/drivers/**").hasRole("DRIVER")
                .antMatchers("/v1/duties/**").hasRole("AGENCY")
                .antMatchers("/v1/entries/**").hasRole("DRIVER")
                .antMatchers("/v1/infringements").hasAnyRole("DRIVER", "AGENCY")
                .and()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .logout()
                .and()
                .headers().frameOptions().disable()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("driver").password(passwordEncoder().encode("driver")).roles("DRIVER")
                .and()
                .withUser("agency").password(passwordEncoder().encode("agency")).roles("AGENCY");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
