package cn.action.modules.bas.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.bas.dao.EnterpriseDao;
import cn.action.modules.bas.entity.Enterprise;

/**
 * ∆Û“µService
 * @author asus
 *
 */
@Service
@Transactional(readOnly=true)
public class EnterpriseService extends CrudService<EnterpriseDao, Enterprise>{

}
