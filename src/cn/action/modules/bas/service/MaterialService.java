package cn.action.modules.bas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.bas.dao.MaterialDao;
import cn.action.modules.bas.entity.Material;

@Service
@Transactional(readOnly = true)
public class MaterialService extends CrudService<MaterialDao, Material> {
@Autowired
private MaterialDao materialDao;
public double findSum(Material material)
{
	System.out.println("############"+material.getmType());
	return materialDao.findSum(material).getSum();
}

}
