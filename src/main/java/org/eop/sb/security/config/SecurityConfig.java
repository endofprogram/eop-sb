package org.eop.sb.security.config;

import java.util.Arrays;

import org.eop.sb.security.access.decision.CompositeAccessDecisionManager;
import org.eop.sb.security.access.decision.RoleAccessDecisionVoter;
import org.eop.sb.security.access.decision.SupportAccessDecisionVoter;
import org.eop.sb.security.access.decision.UriAccessDecisionVoter;
import org.eop.sb.security.access.handler.JsonAccessDeniedHandler;
import org.eop.sb.security.login.handler.JsonAuthenticationFailureHandler;
import org.eop.sb.security.login.handler.JsonAuthenticationSuccessHandler;
import org.eop.sb.security.logout.handler.JsonLogoutSuccessHandler;
import org.eop.sb.security.user.password.FakePasswordEncoder;
import org.eop.sb.security.user.service.UserDetailsServiceImpl;
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
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new FakePasswordEncoder();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.debug(true)
			.ignoring().antMatchers("/images/**", "/js/**", "/css/**")
			.antMatchers("/example/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.accessDecisionManager(new CompositeAccessDecisionManager(
					Arrays.asList(new SupportAccessDecisionVoter(),
							new RoleAccessDecisionVoter(), new UriAccessDecisionVoter())))
			.anyRequest()
				.authenticated()
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
