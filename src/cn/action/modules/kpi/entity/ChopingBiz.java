package cn.action.modules.kpi.entity;

import cn.action.common.persistence.DataEntity;
/**
 * 切片绩效实体类
 * @author Administrator
 *
 */
public class ChopingBiz extends DataEntity<ChopingBiz>{

	private static final long serialVersionUID = 1L;	
	private String time;
	private String employeeName;
	private String pieces;
	private String money;
	private String startTime;
	private String endTime;
	
	public ChopingBiz() {
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
	public String getPieces() {
		return pieces;
	}
	public void setPieces(String pieces) {
		this.pieces = pieces;
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
