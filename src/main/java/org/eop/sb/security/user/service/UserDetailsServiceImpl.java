package org.eop.sb.security.user.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eop.common.hierarchy.HierComputer;
import org.eop.common.hierarchy.IHierComputer;
import org.eop.sb.accesscontrol.bean.Auth;
import org.eop.sb.accesscontrol.bean.Role;
import org.eop.sb.accesscontrol.bean.RoleHier;
import org.eop.sb.accesscontrol.bean.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * <p>从数据库中加载出用户的相关信息
 * @author lixinjie
 * @since 2018-10-30
 */
public class UserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = selectUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("username '" + username + "' not found.");
		}
		
		List<Role> directRoles = selectRolesByUserId(user.getId());
		List<RoleHier> allRoleHiers = selectAllRoleHiers();
		IHierComputer<Long> hierComputer = new HierComputer<Long>(getDirectRoleIds(directRoles), allRoleHiers);
		
		Collection<Long> reachRoleIds = hierComputer.getReachable();
		List<Role> reachRoles = selectRolesByIds(reachRoleIds.toArray(new Long[reachRoleIds.size()]));
		
		Collection<Long> allReachRoleIds = hierComputer.getAllReachable();
		List<Auth> auths = selectAuthsByRoleIds(allReachRoleIds.toArray(new Long[allReachRoleIds.size()]));
		
		user.setDirectRoles(directRoles);
		user.setReachRoles(reachRoles);
		user.setAuths(auths);
		user.basicInit();
		
		return user;
	}
	
	public User selectUserByUsername(String username) {
		return null;
	}

	public List<Role> selectRolesByUserId(Long userId) {
		return null;
	}
	
	public List<RoleHier> selectAllRoleHiers() {
		return null;
	}

	public List<Role> selectRolesByIds(Long[] ids) {
		return null;
	}

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
