package cn.action.modules.bas.entity;

import cn.action.common.persistence.DataEntity;

public class EmployeeTime extends DataEntity<EmployeeTime>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String week;
    private String month;
    private String year;
    private MountGuard MountGuard;
    private String clockIn;
    private String clockOff;
    private String employeeName;
	public MountGuard getMountGuard() {
		return MountGuard;
	}
	public String getClockIn() {
		return clockIn;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public void setClockIn(String clockIn) {
		this.clockIn = clockIn;
	}
	public String getClockOff() {
		return clockOff;
	}
	public void setClockOff(String clockOff) {
		this.clockOff = clockOff;
	}
	public void setMountGuard(MountGuard mountGuard) {
		MountGuard = mountGuard;
	}
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
	}
