package cn.action.modules.equip.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.action.common.persistence.Page;
import cn.action.common.utils.DateUtils;
import cn.action.common.utils.UserUtils;
import cn.action.common.web.BaseController;
import cn.action.common.web.Response;
import cn.action.common.web.SverResponse;
import cn.action.modules.bas.entity.Line;
import cn.action.modules.bas.service.LineService;
import cn.action.modules.equip.entity.EquipFaultReport;
import cn.action.modules.equip.service.EquipFaultReportService;
@Controller
@RequestMapping(value="${adminPath}/equip/report")
public class EquipFaultReportController extends BaseController{
	@Autowired
	private EquipFaultReportService equipFaultReportService;
	@Autowired
	private LineService lineService;
	@ModelAttribute("equipFaultReport")
	public EquipFaultReport get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return equipFaultReportService.get(id);
		}
		return new EquipFaultReport();
	}
	//保存
	@RequestMapping("save")
	public String save(@RequestHeader("Accept") String accept, EquipFaultReport equipFaultReport, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		boolean flag=equipFaultReportService.saveFaultReport(equipFaultReport);
		String message="设备编号有误，无此设备";
		if (flag) {
			message="故障上报成功";
		}
		this.addMessage(redirectAttributes, message);
		
		String json = renderString(response, SverResponse.createRespBySuccessMessage(message));// 转换成json字符串
		String url = "redirect:"+adminPath+"/equip/report/form";// 要返回的url
		return Response.CreateResponse(accept, json, url);// 根据accept参数返回json或url字符串
//		return "redirect:"+adminPath+"/equip/report/form";
	}
	//跳转到故障上报页面
	@RequestMapping("form")
	public String form(HttpServletRequest request, HttpServletResponse response, EquipFaultReport equipFaultReport, Model model) {
		//获得所有产线信息
		List<Line> lines=lineService.findAllList(new Line());
		model.addAttribute("lineList", lines);
		
		String accept = request.getHeader("accept");// 从request中取accept参数（也可用@RequestHeader注解取参数）
		String json = renderString(response, SverResponse.createRespBySuccess(lines));// 转换成json字符串
		String url = "modules/equip/equipFaultReportForm";// 要返回的url
		return Response.CreateResponse(accept, json, url);// 根据accept参数返回json或url字符串

//		return "modules/equip/equipFaultReportForm";
	}
	//分页按条件查询
	@RequestMapping(value= {"list",""})
	public String list(EquipFaultReport equipFaultReport, HttpServletRequest request, HttpServletResponse response, Model model) {
		//获得所有产线信息
		List<Line> lines=lineService.findAllList(new Line());
		model.addAttribute("lineList", lines);
		Page<EquipFaultReport> page=equipFaultReportService.findPage(new Page<EquipFaultReport>(request,response), equipFaultReport);
		model.addAttribute("page", page);
		
		String accept = request.getHeader("accept");// 从request中取accept参数（也可用@RequestHeader注解取参数）
		String json = renderString(response, SverResponse.createRespBySuccess(page));// 转换成json字符串
		String url = "modules/equip/equipFaultReportList";// 要返回的url
		return Response.CreateResponse(accept, json, url);// 根据accept参数返回json或url字符串
		
//		return "modules/equip/equipFaultReportList";
	}
	//派工
	@RequestMapping(value="assign")
	public String assign(HttpServletRequest request, HttpServletResponse response, EquipFaultReport equipFaultReport, Model model, RedirectAttributes redirectAttributes) {
		String json = null;
		String accept = request.getHeader("accept");// 从request中取accept参数（也可用@RequestHeader注解取参数）
		String url = null;
		//判断当前状态
		boolean flag = equipFaultReportService.isAssign(equipFaultReport);
		if (flag) {
			List<Line> lines=lineService.findAllList(new Line());
			model.addAttribute("lineList", lines);
			json = renderString(response, SverResponse.createRespBySuccess(lines));// 转换成json字符串
			url = "modules/equip/equipMaintenanceWorkerForm";
			return Response.CreateResponse(accept, json, url);// 根据accept参数返回json或url字符串
//			return "modules/equip/equipMaintenanceWorkerForm";
		}
		this.addMessage(redirectAttributes, "该报修已得到处理，不能重复派工");
		json = renderString(response, SverResponse.createRespBySuccessMessage("该报修已得到处理，不能重复派工"));// 转换成json字符串
		url = "redirect:"+adminPath+"/equip/report";// 要返回的url
		return Response.CreateResponse(accept, json, url);// 根据accept参数返回json或url字符串
//		return "redirect:"+adminPath+"/equip/report";
	}
	//派工保存
	@RequestMapping(value="maintenance")
	public String maintenance(HttpServletRequest request, HttpServletResponse response, EquipFaultReport equipFaultReport, Model model, RedirectAttributes redirectAttributes) {
		equipFaultReport.setStatus("0002");
		equipFaultReport.setAssignTime(DateUtils.formatDateTime(new Date()));
		equipFaultReportService.save(equipFaultReport);
		this.addMessage(redirectAttributes, "派工成功！");
		
		String accept = request.getHeader("accept");// 从request中取accept参数（也可用@RequestHeader注解取参数）
		String json = renderString(response, SverResponse.createRespBySuccessMessage("派工成功！"));// 转换成json字符串
		String url = "redirect:"+adminPath+"/equip/report";// 要返回的url
		return Response.CreateResponse(accept, json, url);// 根据accept参数返回json或url字符串
		
//		return "redirect:"+adminPath+"/equip/report";
	}
	//分页按条件查询,跳转至设备维修记录界面
	@RequestMapping(value= "repairList")
	public String repairList(HttpServletRequest request, HttpServletResponse response, EquipFaultReport equipFaultReport, Model model) {
		//获得所有产线信息
		List<Line> lines=lineService.findAllList(new Line());
		model.addAttribute("lineList", lines);
		Page<EquipFaultReport> page=equipFaultReportService.findPage(new Page<EquipFaultReport>(request,response), equipFaultReport);
		model.addAttribute("page", page);
		
		String accept = request.getHeader("accept");// 从request中取accept参数（也可用@RequestHeader注解取参数）
		String json = renderString(response, SverResponse.createRespBySuccess(page));// 转换成json字符串
		String url = "modules/equip/equipRepairList";// 要返回的url
		return Response.CreateResponse(accept, json, url);// 根据accept参数返回json或url字符串
		
//		return "modules/equip/equipRepairList";
	}
	//开工
	@RequestMapping(value="start")
	public String startRepair(HttpServletRequest request, HttpServletResponse response, EquipFaultReport equipFaultReport, Model model, RedirectAttributes redirectAttributes) {
		String message = null;
		if (!UserUtils.getUser().getId().equals(equipFaultReport.getMaintenanceWorker())) {//维修人只能开工自己负责的项目
			message="该用户不负责该维修项目，请确认维修人信息";
		}else {
			message = equipFaultReportService.saveStartRepair(equipFaultReport);
		}
		this.addMessage(redirectAttributes, message);
		String accept = request.getHeader("accept");// 从request中取accept参数（也可用@RequestHeader注解取参数）
		String json = renderString(response, SverResponse.createRespBySuccessMessage(message));// 转换成json字符串
		String url = "redirect:"+adminPath+"/equip/report/repairList";// 要返回的url
		return Response.CreateResponse(accept, json, url);// 根据accept参数返回json或url字符串

//		return "redirect:"+adminPath+"/equip/report/repairList";
	}
}
