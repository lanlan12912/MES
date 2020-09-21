package cn.action.modules.bas.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.bas.dao.OrderDao;
import cn.action.modules.bas.entity.Order;

@Service
@Transactional(readOnly=true)
public class OrderService extends CrudService<OrderDao,Order>{
	
	//判断当前订单状态是否是“新订单”
	public boolean isNewOrder(Order order)
	{
		if(order.getStatus().equals("newOrder"))
		{
			return true;
		}
		return false;
	}
}
