package cn.action.modules.qc.service;

import cn.action.common.service.CrudService;
import cn.action.modules.qc.dao.AbnormalProcessDao;
import cn.action.modules.qc.entity.AbnormalProcess;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AbnormalProcessService extends CrudService<AbnormalProcessDao, AbnormalProcess> {

}
