package cn.action.modules.bas.entity;

import cn.action.common.persistence.DataEntity;

public class WorkStationInfos extends DataEntity<WorkStationInfos>{

	/**
	 * ��վ
	 */
	private static final long serialVersionUID = 1L;
	
	private String stationName;//��վ����
	private String stationNo;//��վ����
	private String stationMaster;//��վ������
	private Line line;//����
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getStationNo() {
		return stationNo;
	}
	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}
	public String getStationMaster() {
		return stationMaster;
	}
	public void setStationMaster(String stationMaster) {
		this.stationMaster = stationMaster;
	}
	public Line getLine() {
		return line;
	}
	public void setLine(Line line) {
		this.line = line;
	}
}
