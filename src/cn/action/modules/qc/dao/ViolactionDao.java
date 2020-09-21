package cn.action.modules.qc.dao;

import java.util.List;

import cn.action.common.persistence.CrudDao;
import cn.action.modules.qc.entity.Violaction;

public interface ViolactionDao extends CrudDao<Violaction> {
	// 查询某年某月每个员工的违规次数
	public List<Violaction> findTimes(Violaction violaction);
}
