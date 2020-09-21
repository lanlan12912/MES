package cn.action.modules.tec.web;

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
import cn.action.modules.tec.service.FlowService;

@Controller
@RequestMapping(value="${adminPath}/tec/flow")
public class FlowController extends BaseController{
	@Autowired
	private FlowService flowService;

	
	@ModelAttribute("flow")
	public Flow get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return flowService.get(id);
		}else{
			return new Flow();
		}
	}
	

	@RequestMapping(value = {"list", ""})
	public String list(Flow flow, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Flow> page = flowService.findPage(new Page<Flow>(request, response), flow); 
        model.addAttribute("page", page);
		return "modules/tec/flowList";
	}


	@RequestMapping(value = "form")
	public String form(Flow flow, Model model) {
		model.addAttribute("flow", flow);
		return "modules/tec/flowForm";
	}


	@RequestMapping(value = "save")//@Valid 
	public String save(Flow flow, Model model, RedirectAttributes redirectAttributes) {
		
		flowService.save(flow);
		addMessage(redirectAttributes, "保存工序信息'" + flow.getId()+ "'成功");
		return "redirect:" + adminPath + "/tec/flow/?repage&flowCode="+flow.getFlowCode();
	}
	

	@RequestMapping(value = "delete")
	public String delete(Flow flow, RedirectAttributes redirectAttributes) {
		flowService.delete(flow);
		addMessage(redirectAttributes, "删除工序成功");
		return "redirect:" + adminPath + "/tec/flow/?repage&flowCode="+flow.getFlowCode();
	}
}

