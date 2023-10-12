package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.demo.Repository.UserInfoRepository;
import com.example.demo.Service.UserInfoService;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity

public class SecurityConfig {


	
	@Bean
    // authentication
    public UserDetailsService userDetailsService() {
		/*
		 * UserDetails admin = User.withUsername("tinh")
		 * .password(encoder.encode("tinh1")) .roles("ADMIN") .build(); UserDetails user
		 * = User.withUsername("user") .password(encoder.encode("pwd1")) .roles("USER")
		 * .build(); return new InMemoryUserDetailsManager(admin, user);
		 */
		return new UserInfoService();
    }
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable()
					.authorizeHttpRequests()
					.requestMatchers("/assets/user/**","/user/new").permitAll()
					.requestMatchers("/cline/**").hasAuthority("ROLE_USER")
					.requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
					.anyRequest().permitAll()
					.and()
					.formLogin()
					.loginPage("/security/user")
					.loginProcessingUrl("/j_spring_security_check")
					.successHandler(authenticationSuccessHandler()) 
					.failureUrl("/security/user?error=fail")
					
					.and().build();
	}
    

     @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
     @Bean
     public AuthenticationProvider authenticationProvider(){
         DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
         authenticationProvider.setUserDetailsService(userDetailsService());
         authenticationProvider.setPasswordEncoder(passwordEncoder());
         return authenticationProvider;
     }

     @Bean
     public AuthenticationSuccessHandler authenticationSuccessHandler() {
         return new CustomAuthenticationSuccessHandler();
     }
     
}
