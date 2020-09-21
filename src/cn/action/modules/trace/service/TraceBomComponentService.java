package cn.action.modules.trace.service;
import cn.action.common.service.CrudService;
import cn.action.modules.trace.dao.TraceBomComponentDao;
import cn.action.modules.trace.entity.TraceBomComponent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TraceBomComponentService extends CrudService<TraceBomComponentDao, TraceBomComponent> {
}
