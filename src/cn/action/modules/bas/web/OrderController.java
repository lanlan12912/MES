package cn.action.modules.bas.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.action.common.persistence.Page;
import cn.action.common.web.BaseController;
import cn.action.modules.bas.entity.Order;
import cn.action.modules.bas.service.OrderService;

@Controller
@RequestMapping(value="${adminPath}/bas/order")
public class OrderController extends BaseController{
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="order")
	public Order get(@RequestParam(required=false)String id)
	{
		if(StringUtils.isNotBlank(id))
		{
			return orderService.get(id);
		}
		return new Order();
	}
	
	//分页查询
	@RequestMapping(value= {"list",""})
	public String list(Order order,HttpServletRequest request,HttpServletResponse response,Model model)
	{
		Page<Order> page = orderService.findPage(new Page<Order>(request,response),order);
		model.addAttribute("page", page);
		return "modules/bas/orderList";
	}
	
	//下工单页面跳转
	@RequestMapping(value="doWork")
	public String doWork(Order order,RedirectAttributes redirectAttributes)
	{
		if(!orderService.isNewOrder(order))
		{
			this.addMessage(redirectAttributes,"该订单已经处理,不能重复处理！");
			return "redirect:"+adminPath+"/bas/order";
		}
		return "redirect:"+adminPath+"/bas/workOrder/form?orderId="+order.getId();
	}
}
