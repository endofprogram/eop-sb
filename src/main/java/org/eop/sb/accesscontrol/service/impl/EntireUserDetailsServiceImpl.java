package org.eop.sb.accesscontrol.service.impl;

import java.util.List;

import org.eop.sb.accesscontrol.bean.Auth;
import org.eop.sb.accesscontrol.bean.EntireUserDetails;
import org.eop.sb.accesscontrol.bean.Role;
import org.eop.sb.accesscontrol.service.EntireUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author lixinjie
 * @since 2018-10-23
 */
public class EntireUserDetailsServiceImpl implements EntireUserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = selectUserDetailsByUsername(username);
		if (userDetails == null) {
			throw new UsernameNotFoundException("username '" + username + "' not found.");
		}
		EntireUserDetails entireUserDetails = (EntireUserDetails)userDetails;
		
		List<Role> directRoles = selectRolesByUserId(entireUserDetails.getId());
		entireUserDetails.setDirectRoles(directRoles);
		
		return entireUserDetails;
	}
	
	@Override
	public UserDetails selectUserDetailsByUsername(String username) {
		return null;
	}

	@Override
	public List<Role> selectRolesByUserId(Long userId) {
		return null;
	}

	@Override
	public List<Role> selectRolesByIds(Long[] ids) {
		return null;
	}

	@Override
	public List<Auth> selectAuthsByRoleIds(Long[] ids) {
		return null;
	}

}
