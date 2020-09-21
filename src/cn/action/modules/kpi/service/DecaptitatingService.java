package cn.action.modules.kpi.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.kpi.dao.DecaptitatingDao;
import cn.action.modules.kpi.entity.Decaptitating;

@Service
@Transactional(readOnly=true)
public class DecaptitatingService extends CrudService<DecaptitatingDao,Decaptitating>{
	public List<Decaptitating> findMonthList(Decaptitating decaptitating){
		return this.dao.findMonthList(decaptitating);
	}
}
