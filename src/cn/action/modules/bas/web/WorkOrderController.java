package cn.action.modules.bas.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.druid.stat.TableStat.Mode;

import cn.action.common.persistence.Page;
import cn.action.common.web.BaseController;
import cn.action.modules.bas.entity.Bom;
import cn.action.modules.bas.entity.BomDetail;
import cn.action.modules.bas.entity.Line;
import cn.action.modules.bas.entity.Order;
import cn.action.modules.bas.entity.Product;
import cn.action.modules.bas.entity.WorkOrder;
import cn.action.modules.bas.service.BomDetailService;
import cn.action.modules.bas.service.BomService;
import cn.action.modules.bas.service.LineService;
import cn.action.modules.bas.service.OrderService;
import cn.action.modules.bas.service.ProductService;
import cn.action.modules.bas.service.WorkOrderService;
import cn.action.modules.tec.entity.FlowProcess;
import cn.action.modules.tec.service.FlowProcessService;

@Controller
@RequestMapping(value="${adminPath}/bas/workOrder")
public class WorkOrderController extends BaseController {
	@Autowired
	private WorkOrderService workOrderService;
	@Autowired
	private LineService lineService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private BomService bomService;
	@Autowired
	private BomDetailService bomDetailService;
	@Autowired
	private ProductService productService;
	@Autowired
	private FlowProcessService flowProcessService;
	
	@ModelAttribute("workOrder")
	public WorkOrder get(String id) {
		if(StringUtils.isNoneBlank(id))
		{
			return workOrderService.get(id);
		}
		return new WorkOrder();
	}
	//保存
	@RequestMapping(value="save")
	public String save(WorkOrder workOrder,Model model,RedirectAttributes redirectAttributes)
	{
		System.out.println(workOrder.getState());
		boolean flag=workOrderService.saveAll(workOrder);
		String message=null;
		if(flag) {
			message="保存工单信息成功";
		}
		else
		{
			message="原料不足";
		}
		this.addMessage(redirectAttributes,message);
		return "redirect:"+adminPath+"/bas/workOrder";
	}
	//跳转页面
	@RequestMapping(value="form")
	public String form(WorkOrder workOrder,Model model,RedirectAttributes redirectAttributes,String orderId)
	{
		System.out.println("--------#-----"+orderId);
		//获得订单对象
		Order order=orderService.get(orderId);
		//获得所有产线信息
		List<Line>lines=lineService.findAllList(new Line());
		model.addAttribute("curOrder", order);
		model.addAttribute("lineList", lines);
		model.addAttribute("workOrder", workOrder);
		return "modules/bas/workOrderForm";
	}
	
	//根据条件分页查询
	@RequestMapping(value= {"list",""})
	public String list(WorkOrder workOrder,HttpServletRequest request,HttpServletResponse response,Model model) {
		Page<WorkOrder> page = workOrderService.findPage(new Page<WorkOrder>(request,response), workOrder);
		model.addAttribute("page", page);
		return "modules/bas/workOrderList";
	}
	
	//删除
	@RequestMapping(value="delete")
	public String delete(WorkOrder workOrder,Model model,RedirectAttributes redirectAttributes) {
		String message = null;
		if(workOrderService.deleteOrder(workOrder)) {
			message = "删除工单成功！";
		}else {
			message = "该工单已经发布不能删除！";
		}
		this.addMessage(redirectAttributes, message);
		return "redirect:"+adminPath+"/bas/workOrder";
	}
	
	//跳转到查看工单中对应的bom
	@RequestMapping(value="bom")
	public String bom(WorkOrder workOrder,Model model,HttpServletRequest request,HttpServletResponse response) {
		//1.工单
		model.addAttribute("workOrder", workOrder);
		Bom temp = new Bom();
		temp.setProduct(workOrder.getProduct());
		List<Bom> boms = bomService.findList(temp);
		model.addAttribute("bom", boms.get(0));
		//3.Bom详情
		BomDetail bomDetail = new BomDetail();
		bomDetail.setBom(boms.get(0));
		Page<BomDetail> page = bomDetailService.findPage(new Page<BomDetail>(request,response),bomDetail);
		model.addAttribute("page", page);
		return "modules/bas/workOrderBom";
	}
	
	//跳转到查看工单中产品对应的工艺流程信息
	@RequestMapping(value="flow")
	public String flow(WorkOrder workOrder,Model model,HttpServletRequest request,HttpServletResponse response) {
		//1.工单
		model.addAttribute("workOrder", workOrder);
		//2.工艺流程
		Product product = productService.get(workOrder.getProduct());
		model.addAttribute("product", product);
		//3.工艺流程对应的工序信息
		FlowProcess flowProcess = new FlowProcess();
		flowProcess.setFlow(product.getFlow());
		Page<FlowProcess> page = flowProcessService.findPage(new Page<FlowProcess>(request,response),flowProcess);
		model.addAttribute("page", page);
		return "modules/bas/workOrderFlow";
	}
}
