package org.eop.sb.security.config;

import org.eop.sb.security.access.handler.JsonAccessDeniedHandler;
import org.eop.sb.security.login.handler.JsonAuthenticationFailureHandler;
import org.eop.sb.security.login.handler.JsonAuthenticationSuccessHandler;
import org.eop.sb.security.logout.handler.JsonLogoutSuccessHandler;
import org.eop.sb.security.password.FakePasswordEncoder;
import org.eop.sb.security.service.FakeUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author lixinjie
 * @since 2018-10-17
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new FakeUserDetailsService();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new FakePasswordEncoder();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.debug(true)
			.ignoring().antMatchers("/images/**", "/js/**", "/css/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.mvcMatchers("/index").hasRole("Admin")
				.antMatchers("/admin/**").hasRole("Admin")
				.anyRequest().hasRole("User")
			.and().formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/login-process")
				.successHandler(new JsonAuthenticationSuccessHandler())
				.failureHandler(new JsonAuthenticationFailureHandler())
				.permitAll()
			.and().logout()
				.logoutUrl("/logout-process")
				.logoutSuccessHandler(new JsonLogoutSuccessHandler())
				.permitAll()
			.and().exceptionHandling()
				.accessDeniedHandler(new JsonAccessDeniedHandler());
	}
}
