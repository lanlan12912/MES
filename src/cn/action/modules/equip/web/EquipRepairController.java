package cn.action.modules.equip.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.action.common.utils.UserUtils;
import cn.action.common.web.BaseController;
import cn.action.common.web.Response;
import cn.action.common.web.SverResponse;
import cn.action.modules.equip.entity.EquipRepair;
import cn.action.modules.equip.service.EquipFaultReportService;
import cn.action.modules.equip.service.EquipRepairService;
@Controller
@RequestMapping(value="${adminPath}/equip/repair")
public class EquipRepairController extends BaseController{
	@Autowired
	private EquipRepairService equipRepairService;
	@Autowired
	private EquipFaultReportService equipFaultReportService;
	@ModelAttribute("equipRepair")
	public EquipRepair get(@RequestParam(required=false) String id, String mid) {
		if (StringUtils.isNotBlank(id)) {
			return equipRepairService.get(id);
		}
		EquipRepair repair = new EquipRepair();
		repair.setMid(mid);
		return repair;
	}
	//保存
	@RequestMapping("save")
	public String save(EquipRepair equipRepair,Model model,RedirectAttributes redirectAttributes,@RequestParam("img") MultipartFile file,HttpServletRequest request) throws IllegalStateException, IOException {
		//修改上报记录的状态为“完工”，并且要保存维修报告		
		String filePath="C:\\Users\\27088\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\action3\\static\\imgs\\"+equipRepair.getMid()+".jpg";
		file.transferTo(new File(filePath));
		equipRepairService.saveRepair(equipRepair);
		this.addMessage(redirectAttributes, "维修报告添加成功!");
		return "redirect:"+adminPath+"/equip/report/repairList";
	}
	//跳转到维修报告页面
	@RequestMapping("form")
	public String form(HttpServletRequest request, HttpServletResponse response, EquipRepair equipRepair, Model model) {
		//获得所有产线信息
		model.addAttribute("equipRepair", equipRepair);
		
		String accept = request.getHeader("accept");// 从request中取accept参数（也可用@RequestHeader注解取参数）
		String json = renderString(response, SverResponse.createRespBySuccess(equipRepair));// 转换成json字符串
		String url = "modules/equip/maintenanceReportForm";// 要返回的url
		return Response.CreateResponse(accept, json, url);// 根据accept参数返回json或url字符串
//		return "modules/equip/maintenanceReportForm";
	}
}
