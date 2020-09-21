package cn.action.modules.bas.entity;

import cn.action.common.persistence.DataEntity;

public class WorkCellChange extends DataEntity<WorkCellChange>{
	private static final long serialVersionUID = 1L;
    private String month;
    private String totalTime;
    private String year;
    private String cellName;
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
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
}
