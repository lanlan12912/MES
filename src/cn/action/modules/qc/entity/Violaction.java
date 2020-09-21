package cn.action.modules.qc.entity;

import cn.action.common.persistence.DataEntity;
import cn.action.modules.bas.entity.WorkStationInfos;
import cn.action.modules.tec.entity.Process;

/*
Υ���¼��
 */
public class Violaction extends DataEntity<Violaction> {

	private static final long serialVersionUID = 1L;

	private WorkStationInfos workStationInfos;// ��վ���
	private Process process;// �������
	private String userName;// Υ����Ա
	private String violationDes;// Υ������
	private String violationTime;// Υ��ʱ��
	private int count;// Υ�����

	public Violaction() {
		super();
		this.workStationInfos = new WorkStationInfos();
		this.process = new Process();
	}

	public WorkStationInfos getWorkStationInfos() {
		return workStationInfos;
	}

	public void setWorkStationInfos(WorkStationInfos workStationInfos) {
		this.workStationInfos = workStationInfos;
	}

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getViolationDes() {
		return violationDes;
	}

	public void setViolationDes(String violationDes) {
		this.violationDes = violationDes;
	}

	public String getViolationTime() {
		return violationTime;
	}

	public void setViolationTime(String violationTime) {
		this.violationTime = violationTime;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
