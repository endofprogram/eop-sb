package org.eop.sb.accesscontrol.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eop.common.hierarchy.HierComputer;
import org.eop.common.hierarchy.IHierComputer;
import org.eop.sb.accesscontrol.bean.Auth;
import org.eop.sb.accesscontrol.bean.EntireUserDetails;
import org.eop.sb.accesscontrol.bean.Role;
import org.eop.sb.accesscontrol.bean.RoleHier;
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
		List<RoleHier> allRoleHiers = selectAllRoleHiers();
		IHierComputer<Long> hierComputer = new HierComputer<Long>(getDirectRoleIds(directRoles), allRoleHiers);
		
		Collection<Long> reachRoleIds = hierComputer.getReachable();
		List<Role> reachRoles = selectRolesByIds(reachRoleIds.toArray(new Long[reachRoleIds.size()]));
		
		Collection<Long> allReachRoleIds = hierComputer.getAllReachable();
		List<Auth> auths = selectAuthsByRoleIds(allReachRoleIds.toArray(new Long[allReachRoleIds.size()]));
		
		entireUserDetails.setDirectRoles(directRoles);
		entireUserDetails.setReachRoles(reachRoles);
		entireUserDetails.setAuths(auths);
		entireUserDetails.basicInit();
		
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
	public List<RoleHier> selectAllRoleHiers() {
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

	private List<Long> getDirectRoleIds(List<Role> directRoles) {
		List<Long> roleIds = new ArrayList<>(directRoles.size());
		for (Role role : directRoles) {
			roleIds.add(role.getId());
		}
		return roleIds;
	}
}
