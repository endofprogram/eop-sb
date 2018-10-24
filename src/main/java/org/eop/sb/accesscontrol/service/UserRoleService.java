package org.eop.sb.accesscontrol.service;

import java.util.List;

import org.eop.sb.accesscontrol.bean.UserRole;

/**
 * @author lixinjie
 * @since 2018-10-21
 */
public interface UserRoleService {

	int insertUserRole(UserRole userRole);
	
	int insertUserRoles(List<UserRole> userRoles);
	
	int deleteUserRolesByUserId(Long userId);
	
	int deleteUserRolesByRoleId(Long roleId);
	
	int deleteUserRoleByUserIdRoleId(Long userId, Long roleId);
	
	int deleteUserRolesByUserIdRoleIds(Long userId, Long roleIds[]);
	
	int deleteUserRolesByUserIdsRoleId(Long userIds[], Long roleId);
}
