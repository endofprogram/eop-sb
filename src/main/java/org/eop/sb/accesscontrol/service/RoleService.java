package org.eop.sb.accesscontrol.service;

import java.util.List;

import org.eop.sb.accesscontrol.bean.Role;

/**
 * @author lixinjie
 * @since 2018-10-21
 */
public interface RoleService {

	int insertRole(Role role);
	
	int updateRole(Role role);
	
	int deleteRole(Long id);
	
	Role selectRole(Long id);
	
	List<Role> selectRolesByUserId(Long userId);
	
	List<Role> selectRolesByIds(Long[] ids);
}
