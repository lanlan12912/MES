package cn.action.modules.kpi.entity;

import cn.action.common.persistence.DataEntity;
/**
 * 开片绩效实体类
 *
 */
public class CutPiece extends DataEntity<CutPiece>{

	private static final long serialVersionUID = 1L;	
	private String time;
	private String employeeName;
	private String counter;
	private String money;
	private String startTime;
	private String endTime;
	
	public CutPiece() {
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

	public String getCounter() {
		return counter;
	}
	public void setCounter(String counter) {
		this.counter = counter;
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
