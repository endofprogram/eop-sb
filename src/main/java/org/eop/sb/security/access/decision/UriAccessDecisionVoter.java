package org.eop.sb.security.access.decision;

import java.util.Collection;

import org.eop.sb.security.access.authority.UriGrantedAuthority;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

/**
 * <p>基于uri的投票者
 * @author lixinjie
 * @since 2018-10-22
 */
public class UriAccessDecisionVoter implements AccessDecisionVoter<FilterInvocation> {

	@Override
	public int vote(Authentication authentication, FilterInvocation fi,
			Collection<ConfigAttribute> attributes) {
		String uri = getRequestUri(fi);
		//检查当前用户是否具有该uri，有则通过
		if (authentication.getAuthorities().contains(new UriGrantedAuthority(uri))) {
			return AccessDecisionVoter.ACCESS_GRANTED;
		}
		//拒绝
		return AccessDecisionVoter.ACCESS_DENIED;
	}
	
	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}
	
	private String getRequestUri(FilterInvocation fi) {
		String uri = fi.getRequestUrl();
		int qs = uri.indexOf("?");
		if (qs > 0) {
			return uri.substring(0, qs);
		}
		return uri;
	}
}
