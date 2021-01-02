package com.estagio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.estagio.security.handler.AccessDeniedHandler;
import com.estagio.security.handler.UnauthorizedHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UnauthorizedHandler unauthorizedHandler;
	
	@Autowired
	private AccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	private UserDetailsService userDetailsService; 

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		AuthenticationManager authManager = authenticationManager();
		
		 http
	         .authorizeRequests()
	         .antMatchers(HttpMethod.GET, "/api/v1/login").permitAll()
	         .anyRequest().authenticated()
	         .and().csrf().disable()
	         .addFilter(new JwtAuthenticationFilter(authManager))
             .addFilter(new JwtAuthorizationFilter(authManager, userDetailsService))
             .exceptionHandling()
             .accessDeniedHandler(accessDeniedHandler)
             .authenticationEntryPoint(unauthorizedHandler)
             .and()
             .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
		
	}
	
}
