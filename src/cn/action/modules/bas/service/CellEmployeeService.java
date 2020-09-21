package cn.action.modules.bas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.bas.dao.CellEmployeeDao;
import cn.action.modules.bas.entity.CellEmployee;
import groovy.xml.Entity;
@Service
@Transactional(readOnly=true)
public class CellEmployeeService extends CrudService<CellEmployeeDao, CellEmployee>{
	@Autowired
	private CellEmployeeDao cellEmployeeDao;
	public CellEmployee saveRelation(CellEmployee cellEmployee) {
		//判断是否是添加，若是添加，判断员工是否已绑定工位
		if("".equals(cellEmployee.getId())) {
			//根据员工id判断是否绑定工位
			CellEmployee relation=cellEmployeeDao.findByEmployeeId(cellEmployee);
			if (relation!=null) {
				//该员工已存在关系
				return relation;
			}
		}
		this.save(cellEmployee);
		return null;
	}
}
