package org.eop.sb.security.access.decision;

import java.util.Collection;

import org.eop.sb.security.access.authority.RoleGrantedAuthority;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

/**
 * @author lixinjie
 * @since 2018-10-22
 */
public class RoleAccessDecisionVoter implements AccessDecisionVoter<FilterInvocation> {

	@Override
	public int vote(Authentication authentication, FilterInvocation fi,
			Collection<ConfigAttribute> attributes) {
		String role = "ROLE_Admin";
		if (authentication.getAuthorities().contains(new RoleGrantedAuthority(role))) {
			return AccessDecisionVoter.ACCESS_GRANTED;
		}
		return AccessDecisionVoter.ACCESS_ABSTAIN;
	}
	
	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}
}
