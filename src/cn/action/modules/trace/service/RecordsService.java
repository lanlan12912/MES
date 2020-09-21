package cn.action.modules.trace.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.trace.dao.RecordsDao;
import cn.action.modules.trace.entity.Records;

@Service
@Transactional(readOnly = true)
public class RecordsService extends CrudService<RecordsDao, Records>{

}
