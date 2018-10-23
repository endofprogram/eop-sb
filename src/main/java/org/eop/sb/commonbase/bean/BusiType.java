package org.eop.sb.commonbase.bean;

import java.util.Date;

/**
 * 业务类型，与业务方关联
 * @author lixinjie
 * @since 2018-10-23
 */
public class BusiType {

	private Long id;
	private String name;
	private Integer showOrder;
	private Long busiHostId;
	private Date insertTime;
	private Date updateTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getShowOrder() {
		return showOrder;
	}
	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
	}
	public Long getBusiHostId() {
		return busiHostId;
	}
	public void setBusiHostId(Long busiHostId) {
		this.busiHostId = busiHostId;
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
