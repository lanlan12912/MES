package cn.action.modules.qc.entity;

import cn.action.common.persistence.DataEntity;
import cn.action.modules.bas.entity.Product;
import cn.action.modules.bas.entity.WorkOrder;

public class Inperfections extends DataEntity<Inperfections> {
	/*
	
	 */
	private static final long serialVersionUID = 1L;

	private Product product;// ��Ʒ���
	private String bn;// ����
	private WorkOrder workOrder;// �����������
	private String descc;

	public Inperfections() {
		super();
		this.product = new Product();
		this.workOrder = new WorkOrder();
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getBn() {
		return bn;
	}

	public void setBn(String bn) {
		this.bn = bn;
	}

	public WorkOrder getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}

	public String getDescc() {
		return descc;
	}

	public void setDescc(String descc) {
		this.descc = descc;
	}
}
