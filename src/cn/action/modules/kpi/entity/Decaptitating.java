package cn.action.modules.kpi.entity;

import cn.action.common.persistence.DataEntity;
/**
 * ȥͷ��Чʵ����
 *
 */
public class Decaptitating extends DataEntity<Decaptitating>{

	private static final long serialVersionUID = 1L;	
	private String time;		//����
	private String employeeName; //��Ա����
	private String weight;		//����
	private String money;		//н��
	private String startTime;	//��ʼʱ��
	private String endTime;		//����ʱ��
	
	public Decaptitating() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
