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
import cn.action.modules.tec.entity.Flow;
import cn.action.modules.tec.entity.FlowProcess;
import cn.action.modules.tec.entity.Process;
import cn.action.modules.tec.service.FlowProcessService;
import cn.action.modules.tec.service.FlowService;
import cn.action.modules.tec.service.ProcessService;

@Controller
@RequestMapping(value="${adminPath}/tec/flowProcess")
public class FlowProcessController extends BaseController{
	@Autowired
	private FlowProcessService flowProcessService;
	@Autowired
	private FlowService flowService;
	@Autowired
	private ProcessService processService;
	
	@ModelAttribute("flowProcess")
	public FlowProcess get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return flowProcessService.get(id);
		}else{
			return new FlowProcess();
		}
	}
	

	@RequestMapping(value = {"list", ""})
	public String list(FlowProcess flowProcess, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<FlowProcess> page = flowProcessService.findPage(new Page<FlowProcess>(request, response), flowProcess); 
        model.addAttribute("page", page);
		return "modules/tec/flowProcessList";
	}


	@RequestMapping(value = "form")
	public String form(FlowProcess flowProcess, Model model) {
		List<Flow> flow =flowService.findList(new Flow());
		model.addAttribute("flowList",flow);
		List<Process> process =processService.findList(new Process());
		model.addAttribute("processList",process);
		model.addAttribute("flowProcess", flowProcess);
		return "modules/tec/flowProcessForm";
	}


	@RequestMapping(value = "save")//@Valid 
	public String save(FlowProcess flowProcess, Model model, RedirectAttributes redirectAttributes) {
		flowProcessService.save(flowProcess);
		addMessage(redirectAttributes, "保存工艺路由与工序关系'" + flowProcess.getId()+ "'成功");
		return "redirect:" + adminPath + "/tec/flowProcess";
	}
	

	@RequestMapping(value = "delete")
	public String delete(FlowProcess flowProcess, RedirectAttributes redirectAttributes) {
		flowProcessService.delete(flowProcess);
		addMessage(redirectAttributes, "删除工艺路由与工序关系成功");
		return "redirect:" + adminPath + "/tec/flowProcess";
	}
}

