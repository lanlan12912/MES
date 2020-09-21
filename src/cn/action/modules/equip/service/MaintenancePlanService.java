package cn.action.modules.equip.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.equip.dao.MaintenancePlanDao;
import cn.action.modules.equip.entity.MaintenancePlan;
@Service
@Transactional(readOnly=true)
public class MaintenancePlanService extends CrudService<MaintenancePlanDao, MaintenancePlan>{

}
