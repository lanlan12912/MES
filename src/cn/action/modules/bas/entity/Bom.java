package cn.action.modules.bas.entity;

import cn.action.common.persistence.DataEntity;

public class Bom extends DataEntity<Bom>{

	/**
	 * Bom µÃÂ¿‡
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String bomName;
	private String bomVersion;
	private Product product;

	public Bom(){
		this.product = new Product();
	}
	public String getBomName() {
		return bomName;
	}
	public void setBomName(String bomName) {
		this.bomName = bomName;
	}
	public String getBomVersion() {
		return bomVersion;
	}
	public void setBomVersion(String bomVersion) {
		this.bomVersion = bomVersion;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private String  status;
}
