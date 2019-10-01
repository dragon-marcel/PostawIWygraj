package com.example.PostawIWygraj.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService customUserDetailsService;
 
    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder  passwordEncoder() {
	return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	   http         
	         .headers()
	          .frameOptions().sameOrigin()
	          .and()
	            .authorizeRequests()
	             .antMatchers("/resources/**","/img/**","/h2-console/**", "/webjars/**","/assets/**").permitAll()
	                .antMatchers("/").permitAll()
	                .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
	                .antMatchers("/user/**").hasAnyAuthority("USER")
	                .anyRequest().authenticated()
	                .and()
	            .formLogin()
	                .loginPage("/login")
	                .defaultSuccessUrl("/home")
	                .failureUrl("/login?error")
	                .permitAll()
	                .and()
	            .logout().deleteCookies("JSESSIONID")
	            .and()
	            .rememberMe().key("uniqueAndSecret")
	              .and()
	            .exceptionHandling().accessDeniedPage("/error_403");
	              
    }
    PersistentTokenRepository persistentTokenRepository(){
	     JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
	     tokenRepositoryImpl.setDataSource(dataSource);
	     return tokenRepositoryImpl;
	    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
	return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
         .userDetailsService(customUserDetailsService)
         .passwordEncoder(passwordEncoder());
    }
}
