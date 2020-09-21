package cn.action.modules.tec.entity;

import cn.action.common.persistence.DataEntity;

/**
 * 工序实体类
 * @author Administrator
 *
 */
public class Process extends DataEntity<Process>{

	private static final long serialVersionUID = 1L;
	private String proCode; // 工序名称
	private String proName;// 工序编号
	private String proDesc;// 工序描述

	public Process() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProDesc() {
		return proDesc;
	}

	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}
	
}

