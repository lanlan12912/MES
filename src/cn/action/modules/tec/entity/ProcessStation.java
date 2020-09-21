package cn.action.modules.tec.entity;

import cn.action.common.persistence.DataEntity;
import cn.action.modules.bas.entity.Line;
import cn.action.modules.bas.entity.WorkStationInfos;
/**
 * 工站与工序关系实体类
 * @author Administrator
 *
 */
public class ProcessStation extends DataEntity<ProcessStation>{

	private static final long serialVersionUID = 1L;	
	private WorkStationInfos workStationInfos;
	private Process process;
	private Line line;
	public ProcessStation() {
		super();
		this.workStationInfos = new WorkStationInfos();
		this.process = new Process();
		this.line = new Line();
		// TODO Auto-generated constructor stub
	}
	public WorkStationInfos getWorkStationInfos() {
		return workStationInfos;
	}
	public void setWorkStationInfos(WorkStationInfos workStationInfos) {
		this.workStationInfos = workStationInfos;
	}
	public Process getProcess() {
		return process;
	}
	public void setProcess(Process process) {
		this.process = process;
	}
	public Line getLine() {
		return line;
	}
	public void setLine(Line line) {
		this.line = line;
	}
		
}
