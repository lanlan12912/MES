package cn.action.modules.qc.entity;

import cn.action.common.persistence.DataEntity;

//切片成品率
public class CutPieceYield extends DataEntity<CutPieceYield> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cardId;// 员工号
	private String employeeName;// 员工姓名
	private String workCellId;// 工位id
	// 以下字段数据库表中都没有，自己加的
	private int allQualified;// 不合格的数量
	private int allpieces;// 总片数
	private double yield;// 成品率
	private String startTime;// 开始时间
	private String endTime;// 结束时间

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getWorkCellId() {
		return workCellId;
	}

	public void setWorkCellId(String workCellId) {
		this.workCellId = workCellId;
	}

	public int getAllQualified() {
		return allQualified;
	}

	public void setAllQualified(int allQualified) {
		this.allQualified = allQualified;
	}

	public int getAllpieces() {
		return allpieces;
	}

	public void setAllpieces(int allpieces) {
		this.allpieces = allpieces;
	}

	public double getYield() {
		return yield;
	}

	public void setYield(double yield) {
		this.yield = yield;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
