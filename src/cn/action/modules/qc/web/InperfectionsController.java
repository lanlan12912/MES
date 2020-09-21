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
import cn.action.modules.bas.entity.Product;
import cn.action.modules.bas.entity.WorkOrder;
import cn.action.modules.bas.service.ProductService;
import cn.action.modules.bas.service.WorkOrderService;
import cn.action.modules.qc.entity.Inperfections;
import cn.action.modules.qc.service.InperfectionsService;

@Controller
@RequestMapping(value = "${adminPath}/qc/inperfections")
public class InperfectionsController extends BaseController {

	@Autowired
	private InperfectionsService inperfectionsService;
	@Autowired
	private WorkOrderService workOrderService;
	@Autowired
	private ProductService productService;

	@ModelAttribute("inperfections")
	public Inperfections get(String id) {
		if (StringUtils.isNotBlank(id)) {
			return inperfectionsService.get(id);
		}
		return new Inperfections();
	}

	// ��������ҳ��ѯ
	@RequestMapping(value = { "list", "" })
	public String list(Inperfections inperfections, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<Inperfections> page = inperfectionsService.findPage(new Page<Inperfections>(request, response),
				inperfections);
		model.addAttribute("page", page);

		String accept = request.getHeader("accept");// ��request��ȡaccept������Ҳ����@RequestHeaderע��ȡ������
		String json = renderString(response, SverResponse.createRespBySuccess(page));// ת����json�ַ���
		String url = "modules/qc/inperfectionsList";// Ҫ���ص�url
		return Response.CreateResponse(accept, json, url);// ����accept��������json��url�ַ���
	}

	// ����
	@RequestMapping(value = "save")
	public String save(Inperfections inperfections, Model model, RedirectAttributes redirectAttributes,
			@RequestHeader("Accept") String accept, HttpServletResponse response) {
		WorkOrder workOrder = workOrderService.get(inperfections.getWorkOrder());
		Product product = productService.get(workOrder.getProduct());
		inperfections.setProduct(product);
		inperfectionsService.save(inperfections);

		String json = renderString(response, SverResponse.createRespBySuccessMessage("����д�Ʒ��Ϣ�ɹ���"));
		String url = "redirect:" + adminPath + "/qc/inperfections";
		this.addMessage(redirectAttributes, "����д�Ʒ��Ϣ�ɹ���");
		return Response.CreateResponse(accept, json, url);
	}

	// ɾ��
	@RequestMapping(value = "delete")
	public String delete(Inperfections inperfections, Model model, RedirectAttributes redirectAttributes) {
		inperfectionsService.delete(inperfections);
		this.addMessage(redirectAttributes, "ɾ���д�Ʒ��¼�ɹ�");
		return "redirect:" + adminPath + "/qc/inperfections";
	}

	// ��ת
	@RequestMapping(value = "form")
	public String form(Inperfections inperfections, Model model, @RequestHeader("Accept") String accept,
			HttpServletResponse response) {
		WorkOrder param = new WorkOrder();
		param.setState("unreleased");
		List<WorkOrder> workOrders = workOrderService.findList(param);
		model.addAttribute("workOrderList", workOrders);
		model.addAttribute("inperfections", inperfections);
		// model.addAttribute("iPhoto",iPhoto);
		String json = renderString(response, SverResponse.createRespBySuccess(workOrders));
		String url = "modules/qc/inperfectionsForm";
		return Response.CreateResponse(accept, json, url);
	}
}
