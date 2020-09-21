package cn.action.modules.bas.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.bas.dao.ProductDao;
import cn.action.modules.bas.entity.Product;

@Service
@Transactional(readOnly = true)
public class ProductService extends CrudService<ProductDao, Product> {

}
