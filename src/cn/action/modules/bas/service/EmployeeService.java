package cn.action.modules.bas.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.bas.dao.EmployeeDao;
import cn.action.modules.bas.entity.Employee;
@Service
@Transactional(readOnly=true)
public class EmployeeService extends CrudService<EmployeeDao, Employee>{

}
