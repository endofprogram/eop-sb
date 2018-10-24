package org.eop.sb.accesscontrol.bean;

import java.util.Date;

import org.eop.common.hierarchy.IHierarchy;

/**
 * 角色层次，表示角色间的继承关系
 * @author lixinjie
 * @since 2018-10-21
 */
public class RoleHier implements IHierarchy<Long> {

	private Long id;
	private Long higherRole;
	private Long lowerRole;
	private Date insertTime;
	private Date updateTime;
	
	@Override
	public Long getHigher() {
		return higherRole;
	}
	@Override
	public Long getLower() {
		return lowerRole;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getHigherRole() {
		return higherRole;
	}
	public void setHigherRole(Long higherRole) {
		this.higherRole = higherRole;
	}
	public Long getLowerRole() {
		return lowerRole;
	}
	public void setLowerRole(Long lowerRole) {
		this.lowerRole = lowerRole;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
