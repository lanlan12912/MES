package cn.action.modules.kpi.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.action.common.persistence.Page;
import cn.action.common.utils.StringUtils;
import cn.action.common.web.BaseController;
import cn.action.modules.kpi.entity.PerformType;
import cn.action.modules.kpi.service.PerformTypeService;

@Controller
@RequestMapping(value="${adminPath}/kpi/performType")
public class PerformTypeController extends BaseController{
	@Autowired
	private PerformTypeService performTypeService;
	
	@ModelAttribute("performType")
	public PerformType get(@RequestParam(required=false) String id) {
		if(StringUtils.isNotBlank(id)) {
			return performTypeService.get(id);
		}
		return new PerformType();
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(PerformType performType, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<PerformType> page = performTypeService.findPage(new Page<PerformType>(request, response), performType); 
        model.addAttribute("page", page);
		return "modules/kpi/performTypeList";
	}
	
	//��ת
	@RequestMapping(value = "form")
	public String form(PerformType performType, Model model) {
		model.addAttribute("performType", performType);
		return "modules/kpi/performTypeForm";
	}
	
	//����
	@RequestMapping(value = "save")//@Valid 
	public String save(PerformType performType, Model model, RedirectAttributes redirectAttributes) {
		
		performTypeService.save(performType);
		addMessage(redirectAttributes, "���漨Ч�����ɹ���");
		return "redirect:" + adminPath + "/kpi/performType";
	}
	
	//ɾ��
	@RequestMapping(value = "delete")//@Valid 
	public String delete(PerformType performType, Model model, RedirectAttributes redirectAttributes) {
		
		performTypeService.delete(performType);
		addMessage(redirectAttributes, "ɾ����Ч�����ɹ���");
		return "redirect:" + adminPath + "/kpi/performType";
	}
}
