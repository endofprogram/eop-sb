package org.eop.sb.accesscontrol.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lixinjie
 * @since 2018-10-21
 */
public class Auth {

	private Long id;
	private String authName;
	private String authUrl;
	private String authDesc;
	private Integer showOrder;
	private Integer authLevel;
	private Integer authType;
	private Long parentId;
	private Date insertTime;
	private Date updateTime;
	private List<Auth> children = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAuthName() {
		return authName;
	}
	public void setAuthName(String authName) {
		this.authName = authName;
	}
	public String getAuthUrl() {
		return authUrl;
	}
	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}
	public String getAuthDesc() {
		return authDesc;
	}
	public void setAuthDesc(String authDesc) {
		this.authDesc = authDesc;
	}
	public Integer getShowOrder() {
		return showOrder;
	}
	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
	}
	public Integer getAuthLevel() {
		return authLevel;
	}
	public void setAuthLevel(Integer authLevel) {
		this.authLevel = authLevel;
	}
	public Integer getAuthType() {
		return authType;
	}
	public void setAuthType(Integer authType) {
		this.authType = authType;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
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
	public List<Auth> getChildren() {
		return children;
	}
	public void setChildren(List<Auth> children) {
		this.children = children;
	}
	public void addChild(Auth auth) {
		children.add(auth);
	}
}
