package cn.action.modules.qc.dao;

import java.util.List;

import cn.action.common.persistence.CrudDao;
import cn.action.modules.qc.entity.Violaction;

public interface ViolactionDao extends CrudDao<Violaction> {
	// ��ѯĳ��ĳ��ÿ��Ա����Υ�����
	public List<Violaction> findTimes(Violaction violaction);
}
