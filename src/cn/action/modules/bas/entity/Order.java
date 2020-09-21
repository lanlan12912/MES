package cn.action.modules.bas.entity;

import cn.action.common.persistence.DataEntity;

public class Order extends DataEntity<Order>{
	
	private static final long serialVersionUID=1L;
	
	private String orderNo;
	private String indentor;
	private Product product;//产品外键对象
	private int quantity;
	private String unit;
	private String orderDate;
	private String salesman;
	private String status;
	
	public Order() {
		super();
		this.product=new Product();
	}
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getIndentor() {
		return indentor;
	}
	public void setIndentor(String indentor) {
		this.indentor = indentor;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getSalesman() {
		return salesman;
	}
	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
