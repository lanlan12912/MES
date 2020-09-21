package cn.action.modules.bas.entity;

import cn.action.common.persistence.DataEntity;

public class WorkCellTime extends DataEntity<WorkCellTime>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String week;
    private String month;
    private String year;
    private String totalTime;
    private String cellName;
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getCellName() {
		return cellName;
	}
	public void setCellName(String cellName) {
		this.cellName = cellName;
	}
	public String getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}
}
