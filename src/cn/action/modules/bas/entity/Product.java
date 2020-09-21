package cn.action.modules.bas.entity;

import cn.action.common.persistence.DataEntity;
import cn.action.modules.tec.entity.Flow;

public class Product extends DataEntity<Product> {

	/**
	 * 产品类
	 */
	private static final long serialVersionUID = 1L;
	private String productName;// 产品名称
	private String productAbbr;// 产品简称
	private String productDesc;// 产品描述
	private String firstCheck;// 是否需要首件检查
	private String qrCode;// 条码
	private String manageWay;// 管理方式
	private String lotNumber;// 批次
	private String state;// 状态
	private String productUnit;// 产品计量单位
	private String productProp;// 产品属性
	private Flow flow;// 工艺流程外键对象

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductAbbr() {
		return productAbbr;
	}

	public void setProductAbbr(String productAbbr) {
		this.productAbbr = productAbbr;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getFirstCheck() {
		return firstCheck;
	}

	public void setFirstCheck(String firstCheck) {
		this.firstCheck = firstCheck;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getManageWay() {
		return manageWay;
	}

	public void setManageWay(String manageWay) {
		this.manageWay = manageWay;
	}

	public String getLotNumber() {
		return lotNumber;
	}

	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getProductUnit() {
		return productUnit;
	}

	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}

	public String getProductProp() {
		return productProp;
	}

	public void setProductProp(String productProp) {
		this.productProp = productProp;
	}

	public Flow getFlow() {
		return flow;
	}

	public void setFlow(Flow flow) {
		this.flow = flow;
	}

}
