package org.eop.sb.accesscontrol.service;

import java.util.List;

import org.eop.sb.accesscontrol.bean.RoleAuth;

/**
 * @author lixinjie
 * @since 2018-10-21
 */
public interface RoleAuthService {

	int insertRoleAuth(RoleAuth roleAuth);
	
	int insertRoleAuths(List<RoleAuth> roleAuths);
	
	int deleteRoleAuthsByRoleId(Long roleId);
	
	int deleteRoleAuthsByAuthId(Long authId);
	
	int deleteRoleAuthByRoleIdAuthId(Long roleId, Long authId);
	
	int deleteRoleAuthsByRoleIdAuthIds(Long roleId, Long[] authIds);
	
	int deleteRoleAuthsByRoleIdsAuthId(Long[] roleIds, Long authId);
}
