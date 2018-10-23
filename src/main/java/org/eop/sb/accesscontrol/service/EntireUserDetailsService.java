package org.eop.sb.accesscontrol.service;

import java.util.List;

import org.eop.sb.accesscontrol.bean.Auth;
import org.eop.sb.accesscontrol.bean.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author lixinjie
 * @since 2018-10-23
 */
public interface EntireUserDetailsService extends UserDetailsService {

	UserDetails selectUserDetailsByUsername(String username);
	
	List<Role> selectRolesByUserId(Long userId);
	
	List<Role> selectRolesByIds(Long[] ids);
	
	List<Auth> selectAuthsByRoleIds(Long[] ids);
}
