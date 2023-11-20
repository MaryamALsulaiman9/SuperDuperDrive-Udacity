package com.udacity.jwdnd.course1.cloudstorage.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationService authenticationService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.authenticationService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/signup", "/css/**", "/js/**")
                .permitAll()
                .anyRequest()
                .authenticated();

        http.formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/home", true);

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");
    }
}