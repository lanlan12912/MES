package cn.action.modules.kpi.entity;

import cn.action.common.persistence.DataEntity;
/**
 * 挑刺绩效实体类
 * @author Administrator
 *
 */
public class Boxing extends DataEntity<Boxing>{

	private static final long serialVersionUID = 1L;	
	private String time;
	private String weight;
	private String money;
	private String startTime;
	private String endTime;
	
	public Boxing() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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
