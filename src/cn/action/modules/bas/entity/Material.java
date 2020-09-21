package cn.action.modules.bas.entity;

import cn.action.common.persistence.DataEntity;

public class Material extends DataEntity<Material> {

	/**
	 * ԭ������
	 */
	private static final long serialVersionUID = 1L;
	private String batchNum;// ����
	private String mType;// ��������id
	private String mName;// ��������
	private String supplierId;// ��Ӧ��id
	private String supplierName;// ��Ӧ��
	private String purchaseDate;// �ɹ�����
	private double quantity;// �������֣�
	private String unit;// ��λ
	private double sum;// �����������ܣ������ݿ��ֶΣ��Զ���ģ�

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
