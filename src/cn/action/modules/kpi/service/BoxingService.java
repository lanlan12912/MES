package cn.action.modules.kpi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.kpi.dao.BoxingDao;
import cn.action.modules.kpi.entity.Boxing;

@Service
@Transactional(readOnly=true)
public class BoxingService extends CrudService<BoxingDao,Boxing>{

}
