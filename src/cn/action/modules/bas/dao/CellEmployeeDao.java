package cn.action.modules.bas.dao;

import cn.action.common.persistence.CrudDao;
import cn.action.modules.bas.entity.CellEmployee;

public interface CellEmployeeDao extends CrudDao<CellEmployee>{
	//����Ա��id��ѯ��ϵ����
	public CellEmployee findByEmployeeId(CellEmployee cellEmployee);
}
