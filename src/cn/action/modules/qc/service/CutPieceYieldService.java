package cn.action.modules.qc.service;

import cn.action.common.service.CrudService;
import cn.action.modules.qc.dao.CutPieceYieldDao;
import cn.action.modules.qc.entity.CutPieceYield;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CutPieceYieldService extends CrudService<CutPieceYieldDao, CutPieceYield> {

}
