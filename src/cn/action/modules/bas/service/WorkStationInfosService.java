package cn.action.modules.bas.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.bas.dao.WorkStationInfosDao;
import cn.action.modules.bas.entity.WorkStationInfos;
@Service
@Transactional(readOnly=true)
public class WorkStationInfosService extends CrudService<WorkStationInfosDao,WorkStationInfos>{

	/*
	 * 没有关联绩效参数的工站集合
	 */
	public List<WorkStationInfos> findNoPerform(WorkStationInfos workStationInfos){
		return this.dao.findNoPerform(workStationInfos);
	}
}
