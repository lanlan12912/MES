package cn.action.modules.qc.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.action.common.persistence.Page;
import cn.action.common.utils.StringUtils;
import cn.action.common.web.BaseController;
import cn.action.common.web.Response;
import cn.action.common.web.SverResponse;
import cn.action.modules.bas.entity.Employee;
import cn.action.modules.bas.entity.WorkStationInfos;
import cn.action.modules.bas.service.EmployeeService;
import cn.action.modules.bas.service.WorkStationInfosService;
import cn.action.modules.qc.entity.Violaction;
import cn.action.modules.qc.service.ViolactionService;

@Controller
@RequestMapping(value = "${adminPath}/qc/violaction")
public class ViolactionController extends BaseController {
	@Autowired
	private ViolactionService violactionService;
	@Autowired
	private WorkStationInfosService workStationInfosService;
	@Autowired
	private EmployeeService employeeService;

	@ModelAttribute("violaction")
	public Violaction get(String id) {
		if (StringUtils.isNotBlank(id)) {
			return violactionService.get(id);
		}
		return new Violaction();
	}

	// ��������ҳ��ѯ
	@RequestMapping(value = { "list", "" })
	public String list(Violaction violaction, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Violaction> page = violactionService.findPage(new Page<Violaction>(request, response), violaction);
		model.addAttribute("page", page);

		String accept = request.getHeader("accept");// ��request��ȡaccept������Ҳ����@RequestHeaderע��ȡ������
		String json = renderString(response, SverResponse.createRespBySuccess(page));// ת����json�ַ���
		String url = "modules/qc/violactionList";// Ҫ���ص�url
		return Response.CreateResponse(accept, json, url);// ����accept��������json��url�ַ���
	}

	// ����
	@RequestMapping(value = "save")
	public String save(@RequestHeader("Accept") String accept, HttpServletResponse response, Violaction violaction,
			Model model, RedirectAttributes redirectAttributes) {
		String message = "����Υ���¼�ɹ�";
		String url = "redirect:" + adminPath + "/qc/violaction";
		boolean flag = violactionService.saveViolaction(violaction);
		if (flag == false) {
			message = "�ù�վû�ж�Ӧ���򣡲������Υ����Ϣ��";
			url = "redirect:" + adminPath + "/qc/violaction/form";
		}
		// if (flag == 1) {
		// message = "��Ա���ǹ���Ա�����������Υ����Ϣ��";
		// url = "redirect:" + adminPath + "/qc/violaction/form";
		// }
		this.addMessage(redirectAttributes, message);
		String json = renderString(response, SverResponse.createRespBySuccessMessage(message));
		return Response.CreateResponse(accept, json, url);
	}

	// ɾ��
	@RequestMapping(value = "delete")
	public String delete(Violaction violaction, Model model, RedirectAttributes redirectAttributes) {
		violactionService.delete(violaction);
		this.addMessage(redirectAttributes, "ɾ��Υ���¼�ɹ�");
		return "redirect:" + adminPath + "/qc/violaction";
	}

	// ������ѯ
	@RequestMapping(value = "time")
	public String times(Violaction violaction, Model model) {
		List<Violaction> list = violactionService.findTimes(violaction);
		model.addAttribute("list", list);
		return "modules/qc/violactionTime";
	}

	// ��ת
	@RequestMapping(value = "form")
	public String form(Violaction violaction, Model model, @RequestHeader("Accept") String accept,
			HttpServletResponse response) {
		List<WorkStationInfos> stations = workStationInfosService.findAllList(new WorkStationInfos());
		model.addAttribute("stationList", stations);
		model.addAttribute("violaction", violaction);
		List<Employee> employees = employeeService.findAllList(new Employee());
		model.addAttribute("employeeList", employees);
		String json = renderString(response, SverResponse.createRespBySuccess(stations));
		String url = "modules/qc/violactionForm";
		return Response.CreateResponse(accept, json, url);
	}
}
