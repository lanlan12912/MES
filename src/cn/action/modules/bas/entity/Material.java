package cn.action.modules.bas.entity;

import cn.action.common.persistence.DataEntity;

public class Material extends DataEntity<Material> {

	/**
	 * 原材料类
	 */
	private static final long serialVersionUID = 1L;
	private String batchNum;// 批号
	private String mType;// 材料类型id
	private String mName;// 材料名称
	private String supplierId;// 供应商id
	private String supplierName;// 供应商
	private String purchaseDate;// 采购日期
	private double quantity;// 数量（吨）
	private String unit;// 单位
	private double sum;// 用于数量汇总（非数据库字段，自定义的）

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public String getmType() {
		return mType;
	}

	public void setmType(String mType) {
		this.mType = mType;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}
}
