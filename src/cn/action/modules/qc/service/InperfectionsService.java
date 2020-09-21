package cn.action.modules.qc.service;

import cn.action.common.service.CrudService;
import cn.action.modules.qc.dao.InperfectionsDao;
import cn.action.modules.qc.entity.Inperfections;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class InperfectionsService extends CrudService<InperfectionsDao, Inperfections> {
}
