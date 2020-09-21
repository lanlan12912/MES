package cn.action.modules.bas.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.bas.dao.ElectronSteelyardDao;
import cn.action.modules.bas.entity.ElectronSteelyard;

@Service
@Transactional(readOnly=true)
public class ElectronSteelyardService extends CrudService<ElectronSteelyardDao, ElectronSteelyard>{

}
