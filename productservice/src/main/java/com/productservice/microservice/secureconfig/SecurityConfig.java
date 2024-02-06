package com.productservice.microservice.secureconfig;
 
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.productservice.microservice.service.UserInfoUserDetailsService;
 
@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	JwtAuthFilter jwtAuthFilter;
	
	@Bean
	public UserDetailsService userDetailService()
	{
//		UserDetails admin = User.withUsername("arun")
//			 .password(encoder.encode("hiruthik"))
//			 .roles("admin")
//			 .build();
//		
//		UserDetails user = User.withUsername("murali")
//				.password(encoder.encode("dharan"))
//				.roles("user")
//				.build();
//		
//		return new InMemoryUserDetailsManager(admin,user);
		
		return new UserInfoUserDetailsService();
				
	}
	
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
//		 http
//         .oauth2Client()
//             .and()
//         .oauth2Login()
//         .tokenEndpoint()
//             .and()
//         .userInfoEndpoint();
//
//        http
//         .sessionManagement()
//         .sessionCreationPolicy(null);
		
		SessionCreationPolicy STATELESS = null;
		http.csrf().disable()
		.authorizeHttpRequests((requests) -> requests
			.requestMatchers("/api/product/getreq","v1/user/add-new-user","v1/user/authenticate").permitAll()
			.requestMatchers("api/product/say-good", "api/product/say-nice").authenticated()
//			.requestMatchers("/").hasAnyAuthority("admin")
			.anyRequest().authenticated()
			
		)
        .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authenticationProvider()).addFilterBefore(
				jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
		.formLogin(withDefaults())
		.logout((logout) -> logout.permitAll());
		http.exceptionHandling().accessDeniedPage("/");
		 
	return http.build();
		
		
//	        return http.csrf().disable()
//		   				.authorizeHttpRequests()
//		   				.requestMatchers("").permitAll()
//		   				.and()
//		   				.authorizeHttpRequests()
//		   				.requestMatchers("").authenticated()
//		   				.and()
//		   				.formLogin(withDefaults())
//		   				.build();
//		
	}
	
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http
//		.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated())
//		.httpBasic(withDefaults());
//		return http.build();
//	}
	
	@Bean
	public PasswordEncoder passwordEnoder(){
		return new BCryptPasswordEncoder();
	}
	
	  @Bean
	    public AuthenticationProvider authenticationProvider(){
	        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
	        authenticationProvider.setUserDetailsService(userDetailService());
	        authenticationProvider.setPasswordEncoder(passwordEnoder());
	        return authenticationProvider;
	    }
	  
	  @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }

 
}
 