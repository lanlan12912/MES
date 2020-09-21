package cn.action.modules.equip.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import cn.action.common.persistence.Page;
import cn.action.common.web.BaseController;
import cn.action.common.web.Response;
import cn.action.common.web.SverResponse;
import cn.action.modules.equip.entity.MaintenancePlan;
import cn.action.modules.equip.service.MaintenancePlanService;
@Controller
@RequestMapping(value="${adminPath}/equip/maintenance")
public class MaintenancePlanController extends BaseController{
	@Autowired
	private MaintenancePlanService maintenancePlanService;
	@ModelAttribute("maintenancePlan")
	public MaintenancePlan get(String id) {
		if (StringUtils.isNotBlank(id)) {
			return maintenancePlanService.get(id);
		}
		return new MaintenancePlan();
	}
	//��ҳ��������ѯ�������ƻ�
	@RequestMapping(value= {"list",""})
	public String list(MaintenancePlan maintenancePlan, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MaintenancePlan> page=maintenancePlanService.findPage(new Page<MaintenancePlan>(request,response), maintenancePlan);
		model.addAttribute("page", page);
		
		String accept = request.getHeader("accept");// ��request��ȡaccept������Ҳ����@RequestHeaderע��ȡ������
		String json = renderString(response, SverResponse.createRespBySuccess(page));// ת����json�ַ���
		String url = "modules/equip/maintenancePlanList";// Ҫ���ص�url
		return Response.CreateResponse(accept, json, url);// ����accept��������json��url�ַ���
//		return "modules/equip/maintenancePlanList";
	}
	//����
	@RequestMapping("save")
	public String save(HttpServletRequest request, HttpServletResponse response, MaintenancePlan maintenancePlan, Model model, RedirectAttributes redirectAttributes) {
		maintenancePlanService.save(maintenancePlan);
		this.addMessage(redirectAttributes, "�����豸�����ƻ��ɹ���");
		
		String accept = request.getHeader("accept");// ��request��ȡaccept������Ҳ����@RequestHeaderע��ȡ������
		String json = renderString(response, SverResponse.createRespBySuccessMessage("�����豸�����ƻ��ɹ���"));// ת����json�ַ���
		String url = "redirect:"+adminPath+"/equip/maintenance";// Ҫ���ص�url
		return Response.CreateResponse(accept, json, url);// ����accept��������json��url�ַ���
//		return "redirect:"+adminPath+"/equip/maintenance";
	}
	//ɾ��
	@RequestMapping("delete")
	public String delete(HttpServletRequest request, HttpServletResponse response, MaintenancePlan maintenancePlan, Model model, RedirectAttributes redirectAttributes) {
		maintenancePlanService.delete(maintenancePlan);
		this.addMessage(redirectAttributes, "ɾ���豸�����ƻ��ɹ���");
		
		String accept = request.getHeader("accept");// ��request��ȡaccept������Ҳ����@RequestHeaderע��ȡ������
		String json = renderString(response, SverResponse.createRespBySuccessMessage("ɾ���豸�����ƻ��ɹ���"));// ת����json�ַ���
		String url = "redirect:"+adminPath+"/equip/maintenance";// Ҫ���ص�url
		return Response.CreateResponse(accept, json, url);// ����accept��������json��url�ַ���
//		return "redirect:"+adminPath+"/equip/maintenance";
	}
	@RequestMapping("form")
	public String form(HttpServletRequest request, HttpServletResponse response, MaintenancePlan maintenancePlan, Model model) {
		model.addAttribute("maintenancePlan", maintenancePlan);
		
		String accept = request.getHeader("accept");// ��request��ȡaccept������Ҳ����@RequestHeaderע��ȡ������
		String json = renderString(response, SverResponse.createRespBySuccess(maintenancePlan));// ת����json�ַ���
		String url = "modules/equip/maintenancePlanForm";// Ҫ���ص�url
		return Response.CreateResponse(accept, json, url);// ����accept��������json��url�ַ���
//		return "modules/equip/maintenancePlanForm";
	}
}
