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
		//�ж��Ƿ�����ӣ�������ӣ��ж�Ա���Ƿ��Ѱ󶨹�λ
		if("".equals(cellEmployee.getId())) {
			//����Ա��id�ж��Ƿ�󶨹�λ
			CellEmployee relation=cellEmployeeDao.findByEmployeeId(cellEmployee);
			if (relation!=null) {
				//��Ա���Ѵ��ڹ�ϵ
				return relation;
			}
		}
		this.save(cellEmployee);
		return null;
	}
}
