package cn.action.modules.bas.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.bas.dao.WorkCellChangeDao;
import cn.action.modules.bas.dao.WorkCellTimeDao;
import cn.action.modules.bas.entity.WorkCellChange;
import cn.action.modules.bas.entity.WorkCellTime;

@Service
@Transactional(readOnly=true)
public class WorkCellChangeService extends CrudService<WorkCellChangeDao,WorkCellChange>{
	
}