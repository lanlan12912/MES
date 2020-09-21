package cn.action.modules.bas.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.bas.dao.WorkCellTimeDao;
import cn.action.modules.bas.entity.WorkCellTime;
@Service
@Transactional(readOnly=true)
public class WorkCellTimeService extends CrudService<WorkCellTimeDao,WorkCellTime>{

}
