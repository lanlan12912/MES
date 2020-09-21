package cn.action.modules.kpi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.kpi.dao.PerformTypeDao;
import cn.action.modules.kpi.entity.PerformType;

@Service
@Transactional(readOnly=true)
public class PerformTypeService extends CrudService<PerformTypeDao, PerformType>{

}
