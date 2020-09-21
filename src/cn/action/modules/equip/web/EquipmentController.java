package cn.action.modules.equip.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.action.common.persistence.Page;
import cn.action.common.web.BaseController;
import cn.action.common.web.Response;
import cn.action.common.web.SverResponse;
import cn.action.modules.equip.entity.Equipment;
import cn.action.modules.equip.service.EquipmentService;
@Controller
@RequestMapping(value="${adminPath}/equip/equip")
public class EquipmentController extends BaseController{
	@Autowired
	private EquipmentService equipmentService;
	@ModelAttribute("equipment")
	public Equipment get(String id) {
		if (StringUtils.isNotBlank(id)) {
			return equipmentService.get(id);
		}
		return new Equipment();
	}
	//分页按条件查询，   设备台账
	@RequestMapping(value= {"list",""})
	public String list(Equipment equipment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Equipment> page=equipmentService.findPage(new Page<Equipment>(request,response), equipment);
		model.addAttribute("page", page);
		String accept = request.getHeader("accept");// 从request中取accept参数（也可用@RequestHeader注解取参数）
		String json = renderString(response, SverResponse.createRespBySuccess(page));// 转换成json字符串
		String url = "modules/equip/equipList";// 要返回的url
		return Response.CreateResponse(accept, json, url);// 根据accept参数返回json或url字符串
//		return "modules/equip/equipList";
	}
}
