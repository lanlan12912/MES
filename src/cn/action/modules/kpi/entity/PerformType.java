package cn.action.modules.kpi.entity;

import cn.action.common.persistence.DataEntity;

public class PerformType extends DataEntity<PerformType>{

	private static final long serialVersionUID = 1L;

	private String performTypeName; //��Ч����
	private String performTypeNum;	//��Ч��ֵ
	private String performTypeUnit;	//��Ч��λ
	public String getPerformTypeName() {
		return performTypeName;
	}
	public void setPerformTypeName(String performTypeName) {
		this.performTypeName = performTypeName;
	}
	public String getPerformTypeNum() {
		return performTypeNum;
	}
	public void setPerformTypeNum(String performTypeNum) {
		this.performTypeNum = performTypeNum;
	}
	public String getPerformTypeUnit() {
		return performTypeUnit;
	}
	public void setPerformTypeUnit(String performTypeUnit) {
		this.performTypeUnit = performTypeUnit;
	}
	
}
