package cn.action.modules.bas.dao;

import java.util.List;

import cn.action.common.persistence.CrudDao;
import cn.action.modules.bas.entity.WorkStationInfos;

public interface WorkStationInfosDao extends CrudDao<WorkStationInfos>{
	//查询没有关联绩效参数的工站
	public List<WorkStationInfos> findNoPerform(WorkStationInfos workStationInfos); 
}
