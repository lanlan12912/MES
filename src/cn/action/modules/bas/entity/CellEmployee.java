package cn.action.modules.bas.entity;

import cn.action.common.persistence.DataEntity;

public class CellEmployee extends DataEntity<CellEmployee>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WorkCell workCell;
	private Employee employee;
	public CellEmployee() {
		// TODO Auto-generated constructor stub
		super();
		this.workCell=new WorkCell();
		this.employee=new Employee();
	}
	public WorkCell getWorkCell() {
		return workCell;
	}
	public void setWorkCell(WorkCell workCell) {
		this.workCell = workCell;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	

}
