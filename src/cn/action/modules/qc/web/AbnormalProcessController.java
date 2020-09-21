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

	// 不管调用那个方法先执行这个get方法
	@ModelAttribute("abnormalProcess")
	public AbnormalProcess get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return abnormalProcessService.get(id);
		}
		return new AbnormalProcess();
	}

	// 按条件分页查询
	//分页查询
	@RequestMapping(value = {"list",""})
	public String list(AbnormalProcess abnormalProcess, HttpServletRequest request, HttpServletResponse response, Model model){
		Page<AbnormalProcess> page=abnormalProcessService.findPage(new Page<AbnormalProcess>(request,response),abnormalProcess);
		model.addAttribute("page",page);
		return "modules/qc/abnormalProcessList";
	}

	// 保存
	@RequestMapping(value = "save")
	public String save(AbnormalProcess abnormalProcess, Model model, RedirectAttributes redirectAttributes) {
		// 返回进行重定向，将消息信息作为参数传递到重定向的对象里，可防止表单的重复提交！
		abnormalProcessService.save(abnormalProcess);
		this.addMessage(redirectAttributes, "保存异常工序信息成功！");
		return "redirect:" + adminPath + "/qc/abnormalProcess";// 回到列表页，执行完重新刷新页面
	}

	// 删除
	@RequestMapping(value = "delete")
	public String delete(AbnormalProcess abnormalProcess, Model model, RedirectAttributes redirectAttributes) {
		// 返回进行重定向，将消息信息作为参数传递到重定向的对象里，可防止表单的重复提交！
		abnormalProcessService.delete(abnormalProcess);
		this.addMessage(redirectAttributes, "删除异常工序成功！");
		return "redirect:" + adminPath + "/qc/abnormalProcess";// 回到列表页，执行完重新刷新页面
	}

	// 跳转到添加、修改页面
	@RequestMapping(value = "form")
	public String form(AbnormalProcess abnormalProcess, Model model) {
		// 传递数据：工序集合、用户集合、异常工序
		List<Process> process = processService.findAllList(new Process());
		List<User> users = systemService.findUser(new User());
		model.addAttribute("processList", process);
		model.addAttribute("userList", users);
		model.addAttribute("abnormalProcess", abnormalProcess);
		return "modules/qc/abnormalProcessForm";
	}
}
