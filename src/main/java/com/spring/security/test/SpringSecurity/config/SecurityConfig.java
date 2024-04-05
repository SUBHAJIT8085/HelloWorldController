package com.spring.security.test.SpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
//.requestMatchers("/home/admin").hasRole("ADMIN")
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable().authorizeHttpRequests().
		requestMatchers
		("/home/normal").hasRole("NORMAL").
		requestMatchers("/home/public").permitAll().requestMatchers("/home/user").permitAll().anyRequest()
				.authenticated().and().formLogin();
		return httpSecurity.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		/*UserDetails normalUser = User.withUsername("nandi").password(passwordEncoder().encode("password")).roles("NORMAL")
				.build();

		UserDetails adminUser = User.withUsername("nandi2").password(passwordEncoder().encode("password")).roles("ADMIN")
				.build();

		InMemoryUserDetailsManager im = new InMemoryUserDetailsManager(normalUser, adminUser);*/
		
		return new UserInfoUserDetailsService();

	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}

}
