package cn.action.modules.kpi.dao;

import java.util.List;

import cn.action.common.persistence.CrudDao;
import cn.action.modules.kpi.entity.RemoveFishBone;

public interface RemoveFishBoneDao extends CrudDao<RemoveFishBone>{
	public List<RemoveFishBone> findMonthList(RemoveFishBone removeFishBone);
}
