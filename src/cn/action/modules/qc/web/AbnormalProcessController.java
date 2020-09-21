package cn.action.modules.qc.web;

import cn.action.common.persistence.Page;
import cn.action.common.utils.StringUtils;
import cn.action.common.web.BaseController;
import cn.action.modules.qc.entity.AbnormalProcess;
import cn.action.modules.qc.service.AbnormalProcessService;
import cn.action.modules.sys.entity.User;
import cn.action.modules.sys.service.SystemService;
import cn.action.modules.tec.entity.Process;
import cn.action.modules.tec.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "${adminPath}/qc/abnormalProcess")
public class AbnormalProcessController extends BaseController {
	@Autowired
	private AbnormalProcessService abnormalProcessService;
	@Autowired
	private ProcessService processService;
	@Autowired
	private SystemService systemService;

	// ���ܵ����Ǹ�������ִ�����get����
	@ModelAttribute("abnormalProcess")
	public AbnormalProcess get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return abnormalProcessService.get(id);
		}
		return new AbnormalProcess();
	}

	// ��������ҳ��ѯ
	//��ҳ��ѯ
	@RequestMapping(value = {"list",""})
	public String list(AbnormalProcess abnormalProcess, HttpServletRequest request, HttpServletResponse response, Model model){
		Page<AbnormalProcess> page=abnormalProcessService.findPage(new Page<AbnormalProcess>(request,response),abnormalProcess);
		model.addAttribute("page",page);
		return "modules/qc/abnormalProcessList";
	}

	// ����
	@RequestMapping(value = "save")
	public String save(AbnormalProcess abnormalProcess, Model model, RedirectAttributes redirectAttributes) {
		// ���ؽ����ض��򣬽���Ϣ��Ϣ��Ϊ�������ݵ��ض���Ķ�����ɷ�ֹ�����ظ��ύ��
		abnormalProcessService.save(abnormalProcess);
		this.addMessage(redirectAttributes, "�����쳣������Ϣ�ɹ���");
		return "redirect:" + adminPath + "/qc/abnormalProcess";// �ص��б�ҳ��ִ��������ˢ��ҳ��
	}

	// ɾ��
	@RequestMapping(value = "delete")
	public String delete(AbnormalProcess abnormalProcess, Model model, RedirectAttributes redirectAttributes) {
		// ���ؽ����ض��򣬽���Ϣ��Ϣ��Ϊ�������ݵ��ض���Ķ�����ɷ�ֹ�����ظ��ύ��
		abnormalProcessService.delete(abnormalProcess);
		this.addMessage(redirectAttributes, "ɾ���쳣����ɹ���");
		return "redirect:" + adminPath + "/qc/abnormalProcess";// �ص��б�ҳ��ִ��������ˢ��ҳ��
	}

	// ��ת����ӡ��޸�ҳ��
	@RequestMapping(value = "form")
	public String form(AbnormalProcess abnormalProcess, Model model) {
		// �������ݣ����򼯺ϡ��û����ϡ��쳣����
		List<Process> process = processService.findAllList(new Process());
		List<User> users = systemService.findUser(new User());
		model.addAttribute("processList", process);
		model.addAttribute("userList", users);
		model.addAttribute("abnormalProcess", abnormalProcess);
		return "modules/qc/abnormalProcessForm";
	}
}
