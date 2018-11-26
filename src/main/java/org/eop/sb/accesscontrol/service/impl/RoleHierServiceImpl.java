package org.eop.sb.accesscontrol.service.impl;

import java.util.List;

import org.eop.sb.accesscontrol.bean.RoleHier;
import org.eop.sb.accesscontrol.service.RoleHierService;
import org.springframework.stereotype.Service;

/**
 * @author lixinjie
 * @since 2018-10-21
 */
@Service
public class RoleHierServiceImpl implements RoleHierService {

	@Override
	public int insertRoleHier(RoleHier roleHier) {
		return 0;
	}

	@Override
	public int deleteRoleHier(Long id) {
		return 0;
	}

	@Override
	public RoleHier selectRoleHier(Long id) {
		return null;
	}

	@Override
	public List<RoleHier> selectAllRoleHiers() {
		return null;
	}

	@Override
	public List<RoleHier> selectLowerRoleHiers(Long higherRole) {
		return null;
	}

	@Override
	public List<RoleHier> selectHigherRoleHiers(Long lowerRole) {
		return null;
	}

}
