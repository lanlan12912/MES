package cn.action.modules.tec.web;


import java.util.List;

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
import cn.action.modules.bas.entity.Line;
import cn.action.modules.bas.entity.WorkStationInfos;
import cn.action.modules.bas.service.LineService;
import cn.action.modules.bas.service.WorkStationInfosService;
import cn.action.modules.tec.entity.Process;
import cn.action.modules.tec.entity.ProcessStation;
import cn.action.modules.tec.service.ProcessService;
import cn.action.modules.tec.service.ProcessStationService;

@Controller
@RequestMapping(value="${adminPath}/tec/processStation")
public class ProcessStationController extends BaseController{
	@Autowired
	private ProcessStationService processStationService;
	@Autowired
	private WorkStationInfosService workStationInfosService;
	@Autowired
	private ProcessService processService;
	@Autowired
	private LineService lineService;
	
	@ModelAttribute("processStation")
	public ProcessStation get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return processStationService.get(id);
		}else{
			return new ProcessStation();
		}
	}
	

	@RequestMapping(value = {"list", ""})
	public String list(ProcessStation processStation, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<ProcessStation> page = processStationService.findPage(new Page<ProcessStation>(request, response), processStation); 
        model.addAttribute("page", page);
		return "modules/tec/processStationList";
	}


	@RequestMapping(value = "form")
	public String form(ProcessStation processStation, Model model) {
		List<WorkStationInfos> station =workStationInfosService.findList(new WorkStationInfos());
		model.addAttribute("workStationInfosList",station);
		List<Process> process =processService.findList(new Process());
		model.addAttribute("processList",process);
		List<Line> line =lineService.findList(new Line());
		model.addAttribute("lineList",line);
		model.addAttribute("processStation", processStation);
		return "modules/tec/processStationForm";
	}


	@RequestMapping(value = "save")//@Valid 
	public String save(ProcessStation processStation, Model model, RedirectAttributes redirectAttributes) {
		processStationService.save(processStation);
		addMessage(redirectAttributes, "保存工序与工站关系'" + processStation.getId()+ "'成功");
		return "redirect:" + adminPath + "/tec/processStation";
	}
	

	@RequestMapping(value = "delete")
	public String delete(ProcessStation processStation, RedirectAttributes redirectAttributes) {
		processStationService.delete(processStation);
		addMessage(redirectAttributes, "删除工序与工站关系成功");
		return "redirect:" + adminPath + "/tec/processStation";
	}
}

