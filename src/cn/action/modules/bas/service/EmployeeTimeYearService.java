package cn.action.modules.bas.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.bas.dao.EmployeeTimeDao;
import cn.action.modules.bas.dao.EmployeeTimeYearDao;
import cn.action.modules.bas.entity.EmployeeTime;

@Service
@Transactional(readOnly=true)
public class EmployeeTimeYearService extends CrudService<EmployeeTimeYearDao,EmployeeTime>{

}
