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
import cn.action.modules.bas.entity.Factory;
import cn.action.modules.bas.entity.WorkShop;
import cn.action.modules.bas.service.FactoryService;
import cn.action.modules.bas.service.WorkShopService;

@Controller
@RequestMapping(value = "${adminPath}/bas/workShop")
public class WorkShopController extends BaseController {
	@Autowired
	private WorkShopService workShopService;
	@Autowired
	private FactoryService factoryService;

	// ����͵���
	@ModelAttribute
	private WorkShop get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return workShopService.get(id);
		}
		return new WorkShop();
	}

	// ��ҳ��ѯ
	@RequestMapping(value = { "list", "" })
	public String list(WorkShop workShop, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WorkShop> page = workShopService.findPage(new Page<WorkShop>(request, response), workShop);
		model.addAttribute("page", page);
		return "modules/bas/workShopList";// ����workShopList.jsp
	}

	// ����
	@RequestMapping(value = "save")
	public String save(WorkShop workShop, Model model, RedirectAttributes redirectAttributes) {
		// ���ؽ����ض��򣬽���Ϣ��Ϣ��Ϊ�������ݵ��ض���Ķ�����ɷ�ֹ�����ظ��ύ��
		workShopService.save(workShop);
		this.addMessage(redirectAttributes, "���泵����Ϣ�ɹ���");
		return "redirect:" + adminPath + "/bas/workShop";// �ص��б�ҳ��ִ��������ˢ��ҳ��
	}

	// ɾ��
	@RequestMapping(value = "delete")
	public String delete(WorkShop workShop, RedirectAttributes redirectAttributes) {
		// ���ؽ����ض��򣬽���Ϣ��Ϣ��Ϊ�������ݵ��ض���Ķ�����ɷ�ֹ�����ظ��ύ��
		workShopService.delete(workShop);
		this.addMessage(redirectAttributes, "ɾ������ɹ���");
		return "redirect:" + adminPath + "/bas/workShop";// �ص��б�ҳ��ִ��������ˢ��ҳ��
	}

	// ��ת��workShopForm.jsp
	@RequestMapping(value = "form")
	public String form(WorkShop workShop, Model model) {
		// ������й�������
		List<Factory> factoryList = factoryService.findAllList(new Factory());
		model.addAttribute("factoryList", factoryList);
		model.addAttribute("workShop", workShop);
		return "modules/bas/workShopForm";
	}
}
