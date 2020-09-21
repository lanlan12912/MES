package cn.action.modules.bas.web;

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
import cn.action.modules.bas.entity.Enterprise;
import cn.action.modules.bas.service.EnterpriseService;

@Controller
@RequestMapping(value="${adminPath}/bas/enterprise")
public class EnterpriseController extends BaseController {
	@Autowired
	private EnterpriseService enterpriseService;
	
	//一进入controller先调用的方法，得到的是一个“enterprise”
	@ModelAttribute("enterprise")	
	public Enterprise get(@RequestParam(required=false) String id) {
		if(StringUtils.isNotBlank(id)) {
			return enterpriseService.get(id);
		}
		return new Enterprise();
	}
	//查询
	@RequestMapping(value= {"list",""})
	public String list(Enterprise enterprise,HttpServletRequest request,HttpServletResponse response,Model model) {
		Page<Enterprise> page=enterpriseService.findPage(new Page<Enterprise>(request,response), enterprise);
		model.addAttribute("page",page);
		return "modules/bas/enterpriseList";
	}
	//添加、修改的保存	
	@RequestMapping(value="save")
	public String save(Enterprise enterprise,Model model,RedirectAttributes redirectAttributes) {
		enterpriseService.save(enterprise);
		addMessage(redirectAttributes,"保存企业信息成功！");
		return "redirect:"+adminPath+"/bas/enterprise";
	}
	//删除
	@RequestMapping(value="delete")
	public String delete(Enterprise enterprise,RedirectAttributes redirectAttributes) {
		enterpriseService.delete(enterprise);
		addMessage(redirectAttributes,"删除企业信息成功！");
		return "redirect:"+adminPath+"/bas/enterprise";
	}
	//跳转到企业添加页面enterpriseForm.jsp
	@RequestMapping(value="form")
	public String form(Enterprise enterprise,Model model) {
		model.addAttribute("enterprise",enterprise);
		return "modules/bas/enterpriseForm";
	}
	
}
