package cn.action.modules.tec.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.tec.dao.FlowProcessDao;
import cn.action.modules.tec.entity.FlowProcess;
@Service
@Transactional(readOnly=true)
public class FlowProcessService extends CrudService<FlowProcessDao,FlowProcess>{

}
