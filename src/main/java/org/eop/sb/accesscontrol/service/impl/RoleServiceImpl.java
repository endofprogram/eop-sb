package org.eop.sb.accesscontrol.service.impl;

import java.util.List;

import org.eop.sb.accesscontrol.bean.Role;
import org.eop.sb.accesscontrol.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * @author lixinjie
 * @since 2018-10-21
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Override
	public int insertRole(Role role) {
		return 0;
	}

	@Override
	public int updateRole(Role role) {
		return 0;
	}

	@Override
	public int deleteRole(Long id) {
		return 0;
	}

	@Override
	public Role selectRole(Long id) {
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

}
