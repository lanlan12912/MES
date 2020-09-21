package cn.action.modules.bas.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.bas.dao.BomDetailDao;
import cn.action.modules.bas.entity.BomDetail;
@Service
@Transactional(readOnly=true)
public class BomDetailService extends CrudService<BomDetailDao, BomDetail>{

}
