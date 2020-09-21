package cn.action.modules.equip.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import cn.action.common.persistence.Page;
import cn.action.common.web.BaseController;
import cn.action.common.web.Response;
import cn.action.common.web.SverResponse;
import cn.action.modules.equip.entity.MaintenancePlan;
import cn.action.modules.equip.service.MaintenancePlanService;
@Controller
@RequestMapping(value="${adminPath}/equip/maintenance")
public class MaintenancePlanController extends BaseController{
	@Autowired
	private MaintenancePlanService maintenancePlanService;
	@ModelAttribute("maintenancePlan")
	public MaintenancePlan get(String id) {
		if (StringUtils.isNotBlank(id)) {
			return maintenancePlanService.get(id);
		}
		return new MaintenancePlan();
	}
	//分页按条件查询，保养计划
	@RequestMapping(value= {"list",""})
	public String list(MaintenancePlan maintenancePlan, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MaintenancePlan> page=maintenancePlanService.findPage(new Page<MaintenancePlan>(request,response), maintenancePlan);
		model.addAttribute("page", page);
		
		String accept = request.getHeader("accept");// 从request中取accept参数（也可用@RequestHeader注解取参数）
		String json = renderString(response, SverResponse.createRespBySuccess(page));// 转换成json字符串
		String url = "modules/equip/maintenancePlanList";// 要返回的url
		return Response.CreateResponse(accept, json, url);// 根据accept参数返回json或url字符串
//		return "modules/equip/maintenancePlanList";
	}
	//保存
	@RequestMapping("save")
	public String save(HttpServletRequest request, HttpServletResponse response, MaintenancePlan maintenancePlan, Model model, RedirectAttributes redirectAttributes) {
		maintenancePlanService.save(maintenancePlan);
		this.addMessage(redirectAttributes, "保存设备保养计划成功！");
		
		String accept = request.getHeader("accept");// 从request中取accept参数（也可用@RequestHeader注解取参数）
		String json = renderString(response, SverResponse.createRespBySuccessMessage("保存设备保养计划成功！"));// 转换成json字符串
		String url = "redirect:"+adminPath+"/equip/maintenance";// 要返回的url
		return Response.CreateResponse(accept, json, url);// 根据accept参数返回json或url字符串
//		return "redirect:"+adminPath+"/equip/maintenance";
	}
	//删除
	@RequestMapping("delete")
	public String delete(HttpServletRequest request, HttpServletResponse response, MaintenancePlan maintenancePlan, Model model, RedirectAttributes redirectAttributes) {
		maintenancePlanService.delete(maintenancePlan);
		this.addMessage(redirectAttributes, "删除设备保养计划成功！");
		
		String accept = request.getHeader("accept");// 从request中取accept参数（也可用@RequestHeader注解取参数）
		String json = renderString(response, SverResponse.createRespBySuccessMessage("删除设备保养计划成功！"));// 转换成json字符串
		String url = "redirect:"+adminPath+"/equip/maintenance";// 要返回的url
		return Response.CreateResponse(accept, json, url);// 根据accept参数返回json或url字符串
//		return "redirect:"+adminPath+"/equip/maintenance";
	}
	@RequestMapping("form")
	public String form(HttpServletRequest request, HttpServletResponse response, MaintenancePlan maintenancePlan, Model model) {
		model.addAttribute("maintenancePlan", maintenancePlan);
		
		String accept = request.getHeader("accept");// 从request中取accept参数（也可用@RequestHeader注解取参数）
		String json = renderString(response, SverResponse.createRespBySuccess(maintenancePlan));// 转换成json字符串
		String url = "modules/equip/maintenancePlanForm";// 要返回的url
		return Response.CreateResponse(accept, json, url);// 根据accept参数返回json或url字符串
//		return "modules/equip/maintenancePlanForm";
	}
}
