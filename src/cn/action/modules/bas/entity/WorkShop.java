package cn.action.modules.bas.entity;

import cn.action.common.persistence.DataEntity;

public class WorkShop extends DataEntity<WorkShop> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String shopName;// 车间名称
	private String shopNo;// 车间编码
	private String master;// 车间负责人
	private String description;// 车间描述
	private Factory factory;// 车间所属工厂（外键）

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Factory getFactory() {
		return factory;
	}

	public void setFactory(Factory factory) {
		this.factory = factory;
	}

}
