package cn.action.modules.tec.entity;

import cn.action.common.persistence.DataEntity;

/**
 * 工艺流程实体类
 * @author Administrator
 *
 */
public class Flow extends DataEntity<Flow>{

	private static final long serialVersionUID = 1L;
	private String flowCode; // 流程编码
	private String version;// 版本
	private String flowName;// 流程名称
	private String flowDesc;// 流程描述

	public Flow() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String getFlowDesc() {
		return flowDesc;
	}

	public void setFlowDesc(String flowDesc) {
		this.flowDesc = flowDesc;
	}
	
}

