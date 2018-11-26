package org.eop.sb.accesscontrol.service.impl;

import java.util.List;

import org.eop.sb.accesscontrol.bean.RoleAuth;
import org.eop.sb.accesscontrol.service.RoleAuthService;
import org.springframework.stereotype.Service;

/**
 * @author lixinjie
 * @since 2018-10-21
 */
@Service
public class RoleAuthServiceImpl implements RoleAuthService {

	@Override
	public int insertRoleAuth(RoleAuth roleAuth) {
		return 0;
	}

	@Override
	public int insertRoleAuths(List<RoleAuth> roleAuths) {
		return 0;
	}

	@Override
	public int deleteRoleAuthsByRoleId(Long roleId) {
		return 0;
	}

	@Override
	public int deleteRoleAuthsByAuthId(Long authId) {
		return 0;
	}

	@Override
	public int deleteRoleAuthByRoleIdAuthId(Long roleId, Long authId) {
		return 0;
	}

	@Override
	public int deleteRoleAuthsByRoleIdAuthIds(Long roleId, Long[] authIds) {
		return 0;
	}

	@Override
	public int deleteRoleAuthsByRoleIdsAuthId(Long[] roleIds, Long authId) {
		return 0;
	}

}
