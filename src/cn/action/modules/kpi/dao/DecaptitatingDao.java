package cn.action.modules.kpi.dao;

import java.util.List;

import cn.action.common.persistence.CrudDao;
import cn.action.modules.kpi.entity.Decaptitating;

public interface DecaptitatingDao extends CrudDao<Decaptitating>{
	public List<Decaptitating> findMonthList(Decaptitating decaptitating);
}
