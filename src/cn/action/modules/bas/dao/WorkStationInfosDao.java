package cn.action.modules.bas.dao;

import java.util.List;

import cn.action.common.persistence.CrudDao;
import cn.action.modules.bas.entity.WorkStationInfos;

public interface WorkStationInfosDao extends CrudDao<WorkStationInfos>{
	//��ѯû�й�����Ч�����Ĺ�վ
	public List<WorkStationInfos> findNoPerform(WorkStationInfos workStationInfos); 
}
