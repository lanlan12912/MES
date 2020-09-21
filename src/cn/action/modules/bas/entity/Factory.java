package cn.action.modules.bas.entity;

import cn.action.common.persistence.DataEntity;

public class Factory extends DataEntity<Factory>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String factoryName;
	private String factoryBuildDate;
	private String factoryAddress;
	private String factoryPhone;
	private String factoryECode;
	private String factoryBuildM;
	private Enterprise enterprise;
	
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	public String getFactoryBuildDate() {
		return factoryBuildDate;
	}
	public void setFactoryBuildDate(String factoryBuildDate) {
		this.factoryBuildDate = factoryBuildDate;
	}
	public String getFactoryAddress() {
		return factoryAddress;
	}
	public void setFactoryAddress(String factoryAddress) {
		this.factoryAddress = factoryAddress;
	}
	public String getFactoryPhone() {
		return factoryPhone;
	}
	public void setFactoryPhone(String factoryPhone) {
		this.factoryPhone = factoryPhone;
	}
	public String getFactoryECode() {
		return factoryECode;
	}
	public void setFactoryECode(String factoryECode) {
		this.factoryECode = factoryECode;
	}
	public String getFactoryBuildM() {
		return factoryBuildM;
	}
	public void setFactoryBuildM(String factoryBuildM) {
		this.factoryBuildM = factoryBuildM;
	}
	public Enterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	
	
	
}
