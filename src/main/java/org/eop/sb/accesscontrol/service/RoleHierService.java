package org.eop.sb.accesscontrol.service;

import java.util.List;

import org.eop.sb.accesscontrol.bean.RoleHier;

/**
 * @author lixinjie
 * @since 2018-10-21
 */
public interface RoleHierService {

	int insertRoleHier(RoleHier roleHier);
	
	int deleteRoleHier(Long id);
	
	RoleHier selectRoleHier(Long id);
	
	List<RoleHier> selectAllRoleHiers();
	
	List<RoleHier> selectLowerRoleHiers(Long higherRole);
	
	List<RoleHier> selectHigherRoleHiers(Long lowerRole);
}
