package cn.action.modules.kpi.dao;

import java.util.List;

import cn.action.common.persistence.CrudDao;
import cn.action.modules.kpi.entity.CutPiece;

public interface CutPieceDao extends CrudDao<CutPiece>{
	public List<CutPiece> findMonthList(CutPiece cutPiece);
}
