package cn.action.modules.tec.entity;

import cn.action.common.persistence.DataEntity;

/**
 * ����ʵ����
 * @author Administrator
 *
 */
public class Process extends DataEntity<Process>{

	private static final long serialVersionUID = 1L;
	private String proCode; // ��������
	private String proName;// ������
	private String proDesc;// ��������

	public Process() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProDesc() {
		return proDesc;
	}

	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}
	
}

