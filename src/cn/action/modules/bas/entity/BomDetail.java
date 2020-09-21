package cn.action.modules.bas.entity;

import cn.action.common.persistence.DataEntity;

public class BomDetail extends DataEntity<BomDetail> {

	/**
	 * bomœÍ«È¿‡
	 */
	private static final long serialVersionUID = 1L;
	
	private Bom bom;
	private String mType;
	private double mNum;
	private String unit;
	public Bom getBom() {
		return bom;
	}
	public void setBom(Bom bom) {
		this.bom = bom;
	}
	public String getmType() {
		return mType;
	}
	public void setmType(String mType) {
		this.mType = mType;
	}
	public double getmNum() {
		return mNum;
	}
	public void setmNum(double mNum) {
		this.mNum = mNum;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
}
