package com.example.PostawIWygraj.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.example.PostawIWygraj.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
 
    @Bean
    public PasswordEncoder  passwordEncoder() {
	return new BCryptPasswordEncoder();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
	   http         
	         .headers()
	          .frameOptions().sameOrigin()
	          .and()
	            .authorizeRequests()
	             .antMatchers("/resources/**","/img/**","/css/**","/h2-console/**", "/webjars/**","/assets/**","/registration/**").permitAll()
//	                .antMatchers("/users/**").hasAnyAuthority("ADMIN")
//	                .antMatchers("/useraa/**").hasAnyAuthority("USER")
	                .anyRequest().authenticated().and().
	                cors().and().csrf().disable()                
	            .formLogin()
	                .loginPage("/login")
	                .defaultSuccessUrl("/index") 
	                .failureUrl("/login?error")
	                .permitAll()
	                .and()
	            .logout().and()
	            .exceptionHandling().accessDeniedPage("/error_403")
	            .and()
	            .sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry());
	              
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
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

//    @Bean
//    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
//        return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
//    }
}