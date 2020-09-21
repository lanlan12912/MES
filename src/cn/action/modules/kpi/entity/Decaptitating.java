package cn.action.modules.kpi.entity;

import cn.action.common.persistence.DataEntity;
/**
 * 去头绩效实体类
 *
 */
public class Decaptitating extends DataEntity<Decaptitating>{

	private static final long serialVersionUID = 1L;	
	private String time;		//日期
	private String employeeName; //人员名称
	private String weight;		//总重
	private String money;		//薪资
	private String startTime;	//开始时间
	private String endTime;		//结束时间
	
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
