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
	
	//һ����controller�ȵ��õķ������õ�����һ����enterprise��
	@ModelAttribute("enterprise")	
	public Enterprise get(@RequestParam(required=false) String id) {
		if(StringUtils.isNotBlank(id)) {
			return enterpriseService.get(id);
		}
		return new Enterprise();
	}
	//��ѯ
	@RequestMapping(value= {"list",""})
	public String list(Enterprise enterprise,HttpServletRequest request,HttpServletResponse response,Model model) {
		Page<Enterprise> page=enterpriseService.findPage(new Page<Enterprise>(request,response), enterprise);
		model.addAttribute("page",page);
		return "modules/bas/enterpriseList";
	}
	//��ӡ��޸ĵı���	
	@RequestMapping(value="save")
	public String save(Enterprise enterprise,Model model,RedirectAttributes redirectAttributes) {
		enterpriseService.save(enterprise);
		addMessage(redirectAttributes,"������ҵ��Ϣ�ɹ���");
		return "redirect:"+adminPath+"/bas/enterprise";
	}
	//ɾ��
	@RequestMapping(value="delete")
	public String delete(Enterprise enterprise,RedirectAttributes redirectAttributes) {
		enterpriseService.delete(enterprise);
		addMessage(redirectAttributes,"ɾ����ҵ��Ϣ�ɹ���");
		return "redirect:"+adminPath+"/bas/enterprise";
	}
	//��ת����ҵ���ҳ��enterpriseForm.jsp
	@RequestMapping(value="form")
	public String form(Enterprise enterprise,Model model) {
		model.addAttribute("enterprise",enterprise);
		return "modules/bas/enterpriseForm";
	}
	
}
