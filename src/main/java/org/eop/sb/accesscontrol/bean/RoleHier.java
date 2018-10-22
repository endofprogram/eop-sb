package org.eop.sb.accesscontrol.bean;

import org.eop.common.hierarchy.IHierarchy;

/**
 * 角色层次，表示角色间的继承关系
 * @author lixinjie
 * @since 2018-10-21
 */
public class RoleHier implements IHierarchy<Long> {

	@Override
	public Long getHigher() {
		return null;
	}

	@Override
	public Long getLower() {
		return null;
	}

}
