package com.Java6.config;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Java6.entity.Account;
import com.Java6.security.CustomUserDetailsServiceImpl;
import com.Java6.security.JwtFilter;
import com.Java6.service.AccountService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = false, jsr250Enabled = false)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AccountService accountService;

	@Autowired
	BCryptPasswordEncoder pe;

	@Autowired
	private JwtFilter jwtFilter;


	@Autowired
	private CustomUserDetailsServiceImpl userdetailsService;

	// @Bean
	// public UserDetailsService userdetailsService() {
	// 	return super.userDetailsService();
	// }
	// @Override
	// protected void configure(AuthenticationManagerBuilder auth) throws Exception
	// {
	// auth.userDetailsService(username->{
	// try {
	// Account user = accountService.findById(username);
	// String password = pe.encode(user.getPassword());
	// String[] roles = user.getAuthorities().stream()
	// .map(userRole -> userRole.getRole().getId())
	// .collect(Collectors.toList()).toArray(new String[0]);
	// return User.withUsername(username).password(password).roles(roles).build();
	// }catch (Exception e) {
	// throw new UsernameNotFoundException(username+"not found");
	// }
	// });
	// }

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userdetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
		.antMatchers("/**").permitAll()
		// .and().authorizeRequests().antMatchers("/rest/category/").hasRole("STAF")
		.anyRequest().authenticated()
		.and().exceptionHandling().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		// http.authorizeRequests().antMatchers("/order/**").authenticated().antMatchers("/admin/*")
		// 		.hasAnyRole("STAF", "DIRE").antMatchers("/rest/authorities").hasRole("DIRE").anyRequest().permitAll();

		http.formLogin().loginPage("/security/login/form").loginProcessingUrl("/security/login")
				.defaultSuccessUrl("/security/login/success", false).failureUrl("/security/login/error");

		http.rememberMe().tokenValiditySeconds(1000000);

		http.exceptionHandling().accessDeniedPage("/security/unauthorized");

		http.logout().logoutUrl("/security/logoff").logoutSuccessUrl("/security/logoff/success");

	}

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}
}
