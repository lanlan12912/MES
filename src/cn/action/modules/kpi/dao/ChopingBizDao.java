package cn.action.modules.kpi.dao;

import java.util.List;

import cn.action.common.persistence.CrudDao;
import cn.action.modules.kpi.entity.ChopingBiz;

public interface ChopingBizDao extends CrudDao<ChopingBiz>{
	public List<ChopingBiz> findMonthList(ChopingBiz chopingBiz);
}
