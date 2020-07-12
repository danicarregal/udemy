package com.in28minutes.springboot.configuration.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// 1.-usuarios --> roles
	// vamos a tener los usuarios en memoria.
	/*
	 * For example, the following configuration could be used to register in memory
	 * authentication that exposes an in memory {@link UserDetailsService}:
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// enable in memory based authentication with a user named user and admin
		auth.inMemoryAuthentication().withUser("user").password("secret1").roles("USER").and().withUser("admin")
				.password("secret2").roles("USER", "ADMIN");
	}

	// 2.-autorizacion de los usuarios role --> accesss
	// survey -> USER
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.httpBasic().and().authorizeRequests().antMatchers("/surveys/**").hasRole("USER").antMatchers("/users/**")
				.hasRole("USER").antMatchers("/**").hasRole("ADMIN").and().csrf().disable().headers().frameOptions()
				.disable();
	}
}
