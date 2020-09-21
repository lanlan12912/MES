package cn.action.modules.kpi.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.kpi.dao.ChopingBizDao;
import cn.action.modules.kpi.entity.ChopingBiz;

@Service
@Transactional(readOnly=true)
public class ChopingBizService extends CrudService<ChopingBizDao,ChopingBiz>{
	public List<ChopingBiz> findMonthList(ChopingBiz chopingBiz) {
		return this.dao.findMonthList(chopingBiz);
		
	}
}
