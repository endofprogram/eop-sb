package org.eop.sb.security.access.decision;

import java.util.Collection;

import org.eop.sb.security.access.authority.RoleGrantedAuthority;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

/**
 * <p>基于角色的投票者
 * @author lixinjie
 * @since 2018-10-22
 */
public class RoleAccessDecisionVoter implements AccessDecisionVoter<FilterInvocation> {

	@Override
	public int vote(Authentication authentication, FilterInvocation fi,
			Collection<ConfigAttribute> attributes) {
		//如果当前用户具有管理员角色，直接通过
		if (authentication.getAuthorities().contains(new RoleGrantedAuthority("ROLE_Admin"))) {
			return ACCESS_GRANTED;
		}
		//检查当前用户是否具有要求角色的一个，有则通过
		for (ConfigAttribute attribute : attributes) {
			if (authentication.getAuthorities().contains((Object)attribute)) {
				return ACCESS_GRANTED;
			}
		}
		//弃权
		return ACCESS_ABSTAIN;
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
