package cn.action.modules.equip.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.action.common.persistence.Page;
import cn.action.common.utils.DateUtils;
import cn.action.common.utils.UserUtils;
import cn.action.common.web.BaseController;
import cn.action.common.web.Response;
import cn.action.common.web.SverResponse;
import cn.action.modules.bas.entity.Line;
import cn.action.modules.bas.service.LineService;
import cn.action.modules.equip.entity.EquipFaultReport;
import cn.action.modules.equip.service.EquipFaultReportService;
@Controller
@RequestMapping(value="${adminPath}/equip/report")
public class EquipFaultReportController extends BaseController{
	@Autowired
	private EquipFaultReportService equipFaultReportService;
	@Autowired
	private LineService lineService;
	@ModelAttribute("equipFaultReport")
	public EquipFaultReport get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return equipFaultReportService.get(id);
		}
		return new EquipFaultReport();
	}
	//����
	@RequestMapping("save")
	public String save(@RequestHeader("Accept") String accept, EquipFaultReport equipFaultReport, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		boolean flag=equipFaultReportService.saveFaultReport(equipFaultReport);
		String message="�豸��������޴��豸";
		if (flag) {
			message="�����ϱ��ɹ�";
		}
		this.addMessage(redirectAttributes, message);
		
		String json = renderString(response, SverResponse.createRespBySuccessMessage(message));// ת����json�ַ���
		String url = "redirect:"+adminPath+"/equip/report/form";// Ҫ���ص�url
		return Response.CreateResponse(accept, json, url);// ����accept��������json��url�ַ���
//		return "redirect:"+adminPath+"/equip/report/form";
	}
	//��ת�������ϱ�ҳ��
	@RequestMapping("form")
	public String form(HttpServletRequest request, HttpServletResponse response, EquipFaultReport equipFaultReport, Model model) {
		//������в�����Ϣ
		List<Line> lines=lineService.findAllList(new Line());
		model.addAttribute("lineList", lines);
		
		String accept = request.getHeader("accept");// ��request��ȡaccept������Ҳ����@RequestHeaderע��ȡ������
		String json = renderString(response, SverResponse.createRespBySuccess(lines));// ת����json�ַ���
		String url = "modules/equip/equipFaultReportForm";// Ҫ���ص�url
		return Response.CreateResponse(accept, json, url);// ����accept��������json��url�ַ���

//		return "modules/equip/equipFaultReportForm";
	}
	//��ҳ��������ѯ
	@RequestMapping(value= {"list",""})
	public String list(EquipFaultReport equipFaultReport, HttpServletRequest request, HttpServletResponse response, Model model) {
		//������в�����Ϣ
		List<Line> lines=lineService.findAllList(new Line());
		model.addAttribute("lineList", lines);
		Page<EquipFaultReport> page=equipFaultReportService.findPage(new Page<EquipFaultReport>(request,response), equipFaultReport);
		model.addAttribute("page", page);
		
		String accept = request.getHeader("accept");// ��request��ȡaccept������Ҳ����@RequestHeaderע��ȡ������
		String json = renderString(response, SverResponse.createRespBySuccess(page));// ת����json�ַ���
		String url = "modules/equip/equipFaultReportList";// Ҫ���ص�url
		return Response.CreateResponse(accept, json, url);// ����accept��������json��url�ַ���
		
//		return "modules/equip/equipFaultReportList";
	}
	//�ɹ�
	@RequestMapping(value="assign")
	public String assign(HttpServletRequest request, HttpServletResponse response, EquipFaultReport equipFaultReport, Model model, RedirectAttributes redirectAttributes) {
		String json = null;
		String accept = request.getHeader("accept");// ��request��ȡaccept������Ҳ����@RequestHeaderע��ȡ������
		String url = null;
		//�жϵ�ǰ״̬
		boolean flag = equipFaultReportService.isAssign(equipFaultReport);
		if (flag) {
			List<Line> lines=lineService.findAllList(new Line());
			model.addAttribute("lineList", lines);
			json = renderString(response, SverResponse.createRespBySuccess(lines));// ת����json�ַ���
			url = "modules/equip/equipMaintenanceWorkerForm";
			return Response.CreateResponse(accept, json, url);// ����accept��������json��url�ַ���
//			return "modules/equip/equipMaintenanceWorkerForm";
		}
		this.addMessage(redirectAttributes, "�ñ����ѵõ����������ظ��ɹ�");
		json = renderString(response, SverResponse.createRespBySuccessMessage("�ñ����ѵõ����������ظ��ɹ�"));// ת����json�ַ���
		url = "redirect:"+adminPath+"/equip/report";// Ҫ���ص�url
		return Response.CreateResponse(accept, json, url);// ����accept��������json��url�ַ���
//		return "redirect:"+adminPath+"/equip/report";
	}
	//�ɹ�����
	@RequestMapping(value="maintenance")
	public String maintenance(HttpServletRequest request, HttpServletResponse response, EquipFaultReport equipFaultReport, Model model, RedirectAttributes redirectAttributes) {
		equipFaultReport.setStatus("0002");
		equipFaultReport.setAssignTime(DateUtils.formatDateTime(new Date()));
		equipFaultReportService.save(equipFaultReport);
		this.addMessage(redirectAttributes, "�ɹ��ɹ���");
		
		String accept = request.getHeader("accept");// ��request��ȡaccept������Ҳ����@RequestHeaderע��ȡ������
		String json = renderString(response, SverResponse.createRespBySuccessMessage("�ɹ��ɹ���"));// ת����json�ַ���
		String url = "redirect:"+adminPath+"/equip/report";// Ҫ���ص�url
		return Response.CreateResponse(accept, json, url);// ����accept��������json��url�ַ���
		
//		return "redirect:"+adminPath+"/equip/report";
	}
	//��ҳ��������ѯ,��ת���豸ά�޼�¼����
	@RequestMapping(value= "repairList")
	public String repairList(HttpServletRequest request, HttpServletResponse response, EquipFaultReport equipFaultReport, Model model) {
		//������в�����Ϣ
		List<Line> lines=lineService.findAllList(new Line());
		model.addAttribute("lineList", lines);
		Page<EquipFaultReport> page=equipFaultReportService.findPage(new Page<EquipFaultReport>(request,response), equipFaultReport);
		model.addAttribute("page", page);
		
		String accept = request.getHeader("accept");// ��request��ȡaccept������Ҳ����@RequestHeaderע��ȡ������
		String json = renderString(response, SverResponse.createRespBySuccess(page));// ת����json�ַ���
		String url = "modules/equip/equipRepairList";// Ҫ���ص�url
		return Response.CreateResponse(accept, json, url);// ����accept��������json��url�ַ���
		
//		return "modules/equip/equipRepairList";
	}
	//����
	@RequestMapping(value="start")
	public String startRepair(HttpServletRequest request, HttpServletResponse response, EquipFaultReport equipFaultReport, Model model, RedirectAttributes redirectAttributes) {
		String message = null;
		if (!UserUtils.getUser().getId().equals(equipFaultReport.getMaintenanceWorker())) {//ά����ֻ�ܿ����Լ��������Ŀ
			message="���û��������ά����Ŀ����ȷ��ά������Ϣ";
		}else {
			message = equipFaultReportService.saveStartRepair(equipFaultReport);
		}
		this.addMessage(redirectAttributes, message);
		String accept = request.getHeader("accept");// ��request��ȡaccept������Ҳ����@RequestHeaderע��ȡ������
		String json = renderString(response, SverResponse.createRespBySuccessMessage(message));// ת����json�ַ���
		String url = "redirect:"+adminPath+"/equip/report/repairList";// Ҫ���ص�url
		return Response.CreateResponse(accept, json, url);// ����accept��������json��url�ַ���

//		return "redirect:"+adminPath+"/equip/report/repairList";
	}
}
