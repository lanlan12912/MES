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

	// 进入就调用
	@ModelAttribute
	private WorkShop get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return workShopService.get(id);
		}
		return new WorkShop();
	}

	// 分页查询
	@RequestMapping(value = { "list", "" })
	public String list(WorkShop workShop, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WorkShop> page = workShopService.findPage(new Page<WorkShop>(request, response), workShop);
		model.addAttribute("page", page);
		return "modules/bas/workShopList";// 返回workShopList.jsp
	}

	// 保存
	@RequestMapping(value = "save")
	public String save(WorkShop workShop, Model model, RedirectAttributes redirectAttributes) {
		// 返回进行重定向，将消息信息作为参数传递到重定向的对象里，可防止表单的重复提交！
		workShopService.save(workShop);
		this.addMessage(redirectAttributes, "保存车间信息成功！");
		return "redirect:" + adminPath + "/bas/workShop";// 回到列表页，执行完重新刷新页面
	}

	// 删除
	@RequestMapping(value = "delete")
	public String delete(WorkShop workShop, RedirectAttributes redirectAttributes) {
		// 返回进行重定向，将消息信息作为参数传递到重定向的对象里，可防止表单的重复提交！
		workShopService.delete(workShop);
		this.addMessage(redirectAttributes, "删除车间成功！");
		return "redirect:" + adminPath + "/bas/workShop";// 回到列表页，执行完重新刷新页面
	}

	// 跳转到workShopForm.jsp
	@RequestMapping(value = "form")
	public String form(WorkShop workShop, Model model) {
		// 获得所有工厂对象
		List<Factory> factoryList = factoryService.findAllList(new Factory());
		model.addAttribute("factoryList", factoryList);
		model.addAttribute("workShop", workShop);
		return "modules/bas/workShopForm";
	}
}
