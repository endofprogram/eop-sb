package org.eop.sb.accesscontrol.service.impl;

import java.util.List;

import org.eop.sb.accesscontrol.bean.UserRole;
import org.eop.sb.accesscontrol.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * @author lixinjie
 * @since 2018-10-21
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Override
	public int insertUserRole(UserRole userRole) {
		return 0;
	}

	@Override
	public int insertUserRoles(List<UserRole> userRoles) {
		return 0;
	}

	@Override
	public int deleteUserRolesByUserId(Long userId) {
		return 0;
	}

	@Override
	public int deleteUserRolesByRoleId(Long roleId) {
		return 0;
	}

	@Override
	public int deleteUserRoleByUserIdRoleId(Long userId, Long roleId) {
		return 0;
	}

	@Override
	public int deleteUserRolesByUserIdRoleIds(Long userId, Long[] roleIds) {
		return 0;
	}

	@Override
	public int deleteUserRolesByUserIdsRoleId(Long[] userIds, Long roleId) {
		return 0;
	}

}
