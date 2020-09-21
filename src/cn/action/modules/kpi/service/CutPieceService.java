package cn.action.modules.kpi.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.kpi.dao.CutPieceDao;
import cn.action.modules.kpi.entity.CutPiece;

@Service
@Transactional(readOnly=true)
public class CutPieceService extends CrudService<CutPieceDao,CutPiece>{
	public List<CutPiece> findMonthList(CutPiece cutPiece){
		return this.dao.findMonthList(cutPiece);
	}
}
