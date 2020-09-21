package cn.action.modules.tec.entity;

import cn.action.common.persistence.DataEntity;
/**
 * 工艺路由与工序关系实体类
 * @author Administrator
 *
 */
public class FlowProcess extends DataEntity<FlowProcess>{

	private static final long serialVersionUID = 1L;	
	private Flow flow;
	private Process process;
	private int sort;
	public FlowProcess() {
		super();
		this.flow = new Flow();
		this.process = new Process();
		// TODO Auto-generated constructor stub
	}
	public Flow getFlow() {
		return flow;
	}
	public void setFlow(Flow flow) {
		this.flow = flow;
	}
	public Process getProcess() {
		return process;
	}
	public void setProcess(Process process) {
		this.process = process;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	
}
