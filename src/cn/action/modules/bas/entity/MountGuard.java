package cn.action.modules.bas.entity;

import cn.action.common.persistence.DataEntity;
import cn.action.modules.sys.entity.Office;

/**
 * 员工工作记录类
 * @author asus
 *
 */
public class MountGuard extends DataEntity<MountGuard>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MountGuard() {
		super();
		this.employee = new Employee();
		this.office = new Office();
		this.line = new Line();
		this.workStationInfos = new WorkStationInfos();
		this.workCell = new WorkCell();
	}
	
	private Employee employee;
	private Office office;
	private Line line;
	private WorkStationInfos workStationInfos;
	private WorkCell workCell;
	private String clockIn;//上班时间
	private String clockOff;//下班时间
	private String workStatus;//工作状态

	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	public Line getLine() {
		return line;
	}
	public void setLine(Line line) {
		this.line = line;
	}
	public WorkStationInfos getWorkStationInfos() {
		return workStationInfos;
	}
	public void setWorkStationInfos(WorkStationInfos workStationInfos) {
		this.workStationInfos = workStationInfos;
	}
	public WorkCell getWorkCell() {
		return workCell;
	}
	public void setWorkCell(WorkCell workCell) {
		this.workCell = workCell;
	}
	public String getclockIn() {
		return clockIn;
	}
	public void setclockIn(String clockIn) {
		this.clockIn = clockIn;
	}
	public String getclockOff() {
		return clockOff;
	}
	public void setclockOff(String clockOff) {
		this.clockOff = clockOff;
	}
	public String getWorkStatus() {
		return workStatus;
	}
	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	
}
