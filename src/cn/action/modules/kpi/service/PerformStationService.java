package cn.action.modules.kpi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.kpi.dao.PerformStationDao;
import cn.action.modules.kpi.entity.PerformStation;

@Service
@Transactional(readOnly=true)
public class PerformStationService extends CrudService<PerformStationDao, PerformStation>{
}
