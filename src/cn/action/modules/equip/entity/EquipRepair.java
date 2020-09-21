package cn.action.modules.equip.entity;

import cn.action.common.persistence.DataEntity;

public class EquipRepair extends DataEntity<EquipRepair>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mid;//�ϱ���¼���
	private String faultType;//��������
	private String faultReason;//����ԭ��
	private String faultDesc;//��������
	private String faultImgs;//ͼƬ��ַ
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getFaultType() {
		return faultType;
	}
	public void setFaultType(String faultType) {
		this.faultType = faultType;
	}
	public String getFaultReason() {
		return faultReason;
	}
	public void setFaultReason(String faultReason) {
		this.faultReason = faultReason;
	}
	public String getFaultDesc() {
		return faultDesc;
	}
	public void setFaultDesc(String faultDesc) {
		this.faultDesc = faultDesc;
	}
	public String getFaultImgs() {
		return faultImgs;
	}
	public void setFaultImgs(String faultImgs) {
		this.faultImgs = faultImgs;
	}
	
}
