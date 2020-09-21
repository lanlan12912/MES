package cn.action.modules.bas.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.bas.dao.BomDao;
import cn.action.modules.bas.entity.Bom;
@Service
@Transactional(readOnly=true)
public class BomService extends CrudService<BomDao, Bom>{

}
