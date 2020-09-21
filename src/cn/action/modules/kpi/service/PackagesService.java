package cn.action.modules.kpi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.kpi.dao.PackagesDao;
import cn.action.modules.kpi.entity.Packages;

@Service
@Transactional(readOnly=true)
public class PackagesService extends CrudService<PackagesDao,Packages>{

}
