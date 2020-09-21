package cn.action.modules.bas.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.bas.dao.FactoryDao;
import cn.action.modules.bas.entity.Factory;

@Service
@Transactional(readOnly=true)
public class FactoryService extends CrudService<FactoryDao, Factory>{

}
