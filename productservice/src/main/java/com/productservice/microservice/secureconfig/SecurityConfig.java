package com.productservice.microservice.secureconfig;
 
import static org.springframework.security.config.Customizer.withDefaults;
 
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
 
@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	
	
	@Bean
	public UserDetailsService userDetailService(PasswordEncoder encoder)
	{
		UserDetails admin = User.withUsername("arun")
			 .password(encoder.encode("hiruthik"))
			 .roles("admin")
			 .build();
		
		UserDetails user = User.withUsername("murali")
				.password(encoder.encode("dharan"))
				.roles("user")
				.build();
		
		return new InMemoryUserDetailsManager(admin,user);
				
	}
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http
		.authorizeHttpRequests((requests) -> requests
			.requestMatchers("/api/product/getreq").permitAll()
			.requestMatchers("api/product/say-good").authenticated()
//			.requestMatchers("/").hasAnyAuthority("admin")
			.anyRequest().authenticated()
		)
		.formLogin((form) -> form
			.loginPage("")
			.permitAll()
		)
		.logout((logout) -> logout.permitAll());
		 
	return http.build();
		
		
//		return http.csrf().disable()
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
 
}
 