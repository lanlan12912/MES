package cn.action.modules.equip.entity;

import cn.action.common.persistence.DataEntity;

public class EquipFaultReport extends DataEntity<EquipFaultReport>{

	/**�豸�����ϱ�����
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String equipId;//�豸id
	private String equipNo;//�豸���
	private String equipType;//�豸����
	private String equipLoc;//��������
	private String faultDesc;//��������
	private String status;//״̬
	private String reportPerson;//�ϱ���
	private String assignTime;//�ɹ�ʱ��
	private String maintenanceWorker;//ά����
	private EquipRepair equipRepair;//ά�ޱ���
	public EquipFaultReport() {
		// TODO Auto-generated constructor stub
		super();
		this.equipRepair=new EquipRepair();
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getEquipNo() {
		return equipNo;
	}
	public void setEquipNo(String equipNo) {
		this.equipNo = equipNo;
	}
	public String getEquipType() {
		return equipType;
	}
	public void setEquipType(String equipType) {
		this.equipType = equipType;
	}
	public String getEquipLoc() {
		return equipLoc;
	}
	public void setEquipLoc(String equipLoc) {
		this.equipLoc = equipLoc;
	}
	public String getFaultDesc() {
		return faultDesc;
	}
	public void setFaultDesc(String faultDesc) {
		this.faultDesc = faultDesc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReportPerson() {
		return reportPerson;
	}
	public void setReportPerson(String reportPerson) {
		this.reportPerson = reportPerson;
	}
	public String getAssignTime() {
		return assignTime;
	}
	public void setAssignTime(String assignTime) {
		this.assignTime = assignTime;
	}
	public String getMaintenanceWorker() {
		return maintenanceWorker;
	}
	public void setMaintenanceWorker(String maintenanceWorker) {
		this.maintenanceWorker = maintenanceWorker;
	}
	public EquipRepair getEquipRepair() {
		return equipRepair;
	}
	public void setEquipRepair(EquipRepair equipRepair) {
		this.equipRepair = equipRepair;
	}
}
