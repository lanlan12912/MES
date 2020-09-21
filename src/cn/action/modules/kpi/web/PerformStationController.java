package cn.action.modules.kpi.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.action.common.persistence.Page;
import cn.action.common.utils.StringUtils;
import cn.action.common.web.BaseController;
import cn.action.modules.bas.entity.WorkStationInfos;
import cn.action.modules.bas.service.WorkStationInfosService;
import cn.action.modules.kpi.entity.PerformStation;
import cn.action.modules.kpi.entity.PerformType;
import cn.action.modules.kpi.service.PerformStationService;
import cn.action.modules.kpi.service.PerformTypeService;
@Controller
@RequestMapping(value="${adminPath}/kpi/performStation")
public class PerformStationController extends BaseController{
	
	@Autowired
	private PerformStationService performStationService;
	@Autowired
	private PerformTypeService PerformTypeService;
	@Autowired
	private WorkStationInfosService WorkStationInfosService;
	
	@ModelAttribute("performStation")
	public PerformStation get(String id) {
		if(StringUtils.isNotBlank(id)) {
			return performStationService.get(id);
		}
		return new PerformStation();
	}
	
	@RequestMapping(value= {"list",""})
	public String list(PerformStation performStation,HttpServletRequest request,HttpServletResponse response,Model model) {
		Page<PerformStation> page = performStationService.findPage(new Page<>(request, response),performStation);
		model.addAttribute("page",page);
		return "modules/kpi/performStationList";
		
	}
	
	//����
	@RequestMapping(value = "save")//@Valid 
	public String save(PerformStation performStation, Model model, RedirectAttributes redirectAttributes) {
		
		performStationService.save(performStation);
		this.addMessage(redirectAttributes, "���漨Ч�����빤վ��ϵ�ɹ�");
		return "redirect:" + adminPath + "/kpi/performStation";
	}
	//ɾ��
	@RequestMapping(value = "delete")//@Valid 
	public String delete(PerformStation performStation, Model model, RedirectAttributes redirectAttributes) {
		
		performStationService.delete(performStation);
		this.addMessage(redirectAttributes, "ɾ����Ч�����빤վ��ϵ�ɹ�");
		return "redirect:" + adminPath + "/kpi/performStation";
	}
	//��ת
	@RequestMapping(value = "form")
	public String form(PerformStation performStation, Model model) {
		List<PerformType> performTypes = PerformTypeService.findAllList(new PerformType());
		List<WorkStationInfos> stations =  WorkStationInfosService.findNoPerform(new WorkStationInfos());
		if(performStation.getWorkStationInfos().getId()!=null && !performStation.getWorkStationInfos().getId().equals("")) {
			stations.add(performStation.getWorkStationInfos());
		}
		model.addAttribute("performTypeList",performTypes);
		model.addAttribute("stationList", stations);
		model.addAttribute("performStation", performStation);
		return "modules/kpi/performStationForm";
	}
}
