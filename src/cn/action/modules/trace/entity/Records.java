package cn.action.modules.trace.entity;

import cn.action.common.persistence.DataEntity;

public class Records extends DataEntity<Records>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String lotNumber;	//���Σ���bas_product
	private String productName;	//��Ʒ���ƣ���bas_product
	private String proCode;		//�����ţ���tec_process
	private String proName;		//�������ƣ���tec_process
	private String cellNumber;	//��λ��ţ���bas_workcell
	private String cellName;	//��λ���ƣ���bas_workcell
	private String employeeNo;	//Ա����ţ���bas_employee
	private String employeeName;	//Ա��������bas_employee
	
	public String getLotNumber() {
		return lotNumber;
	}
	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
	public String getCellNumber() {
		return cellNumber;
	}
	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}
	public String getCellName() {
		return cellName;
	}
	public void setCellName(String cellName) {
		this.cellName = cellName;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

}
