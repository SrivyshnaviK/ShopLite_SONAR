package com.zemoso.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder)throws Exception{
        User.UserBuilder users= User.withDefaultPasswordEncoder();
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser(users.username("Srivyshnavi").password("vyshnavi").roles("Admin"));
    }
    @Override
    protected void configure(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.authorizeRequests()
                .antMatchers("/shoplite").permitAll()
                .antMatchers("/shoplite/admin/**").hasRole("Admin")
                .and().formLogin().loginPage("/shoplitelogin/showLoginPage")
                .loginProcessingUrl("/authenticateUser").permitAll().permitAll()
                .and().logout().logoutUrl("/shoplite/logout").logoutSuccessUrl("/shoplite").permitAll().and().exceptionHandling().accessDeniedPage("/shoplitelogin/accessDeniedPage");;
    }
}
