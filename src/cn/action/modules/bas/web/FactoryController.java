package cn.action.modules.bas.web;

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
import cn.action.modules.bas.entity.Enterprise;
import cn.action.modules.bas.entity.Factory;
import cn.action.modules.bas.service.EnterpriseService;
import cn.action.modules.bas.service.FactoryService;

@Controller
@RequestMapping(value="${adminPath}/bas/factory")
public class FactoryController extends BaseController{
	@Autowired
	private FactoryService factoryService;
	@Autowired
	private EnterpriseService enterService;
	
	//һ�����ͨ��id���factory����
	@ModelAttribute("factory")
	public Factory get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return factoryService.get(id);
		}
		return new Factory();
	}
	
	//��ѯ
	@RequestMapping(value = {"list", ""})
	public String list(Factory factory, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Factory> page = factoryService.findPage(new Page<Factory>(request, response), factory); 
        model.addAttribute("page", page);
		return "modules/bas/factoryList";
	}

	//��ӹ���
	@RequestMapping(value = "save")
	public String save(Factory factory, Model model, RedirectAttributes redirectAttributes) {
		factoryService.save(factory);
		addMessage(redirectAttributes, "���湤��'" + factory.getFactoryName() + "'�ɹ�");
		return "redirect:" + adminPath + "/bas/factory";
	}
	
	//ɾ������
	@RequestMapping(value = "delete")
	public String delete(Factory factory, RedirectAttributes redirectAttributes) {
		System.out.println("before delete: ID->"+factory.getId()+" NAME->"+factory.getFactoryName()+" DEL->"+factory.getDelFlag());
		factoryService.delete(factory);
		System.out.println("after delete: ID->"+factory.getId()+" NAME->"+factory.getFactoryName()+" DEL->"+factory.getDelFlag());
		addMessage(redirectAttributes, "ɾ�������ɹ�");
		return "redirect:" + adminPath + "/bas/factory";
	}
	
	//���ڲ����ɹ�����תҳ��
	@RequestMapping(value = "form")
	public String form(Factory factory, Model model) {
		List<Enterprise> enters =enterService.findList(new Enterprise());
		model.addAttribute("enterpriseList",enters);
		model.addAttribute("factory", factory);
		return "modules/bas/factoryForm";
	}


}
