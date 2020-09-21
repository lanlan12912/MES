package cn.action.modules.qc.entity;

import cn.action.common.persistence.DataEntity;
import cn.action.modules.sys.entity.User;
import cn.action.modules.tec.entity.Process;

/**
 * 异常工序检测类
 * 
 * @author 15173
 *
 */
public class AbnormalProcess extends DataEntity<AbnormalProcess> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// private String pro_id;此为外键,tec_process中的id
	private Process process;// 工序外键对象
	private String problem;// 上报问题
	private User reportUser;// 上报人
	private String reportTime;// 上报时间
	private String test;// 检查结果
	private User testUser;// 检查人
	private String testTime;// 检查时间
	private String state;// 状态

	// 外键添加构造方法
	public AbnormalProcess() {
		this.process = new Process();
		this.reportUser = new User();
		this.testUser = new User();
	}

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public User getReportUser() {
		return reportUser;
	}

	public void setReportUser(User reportUser) {
		this.reportUser = reportUser;
	}

	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public User getTestUser() {
		return testUser;
	}

	public void setTestUser(User testUser) {
		this.testUser = testUser;
	}

	public String getTestTime() {
		return testTime;
	}

	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
