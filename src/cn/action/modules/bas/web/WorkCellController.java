package cn.action.modules.bas.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.action.common.persistence.Page;
import cn.action.common.web.BaseController;
import cn.action.modules.bas.entity.WorkCell;
import cn.action.modules.bas.entity.WorkStationInfos;
import cn.action.modules.bas.service.WorkCellService;
import cn.action.modules.bas.service.WorkStationInfosService;
@Controller
@RequestMapping(value="${adminPath}/bas/workCell")
public class WorkCellController extends BaseController{
	@Autowired
	private WorkCellService workCellService;
	@Autowired
	private WorkStationInfosService workStationInfosService;
	@ModelAttribute("workCell")
	public WorkCell get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return workCellService.get(id);
		}
		return new WorkCell();
	}
	//��ҳ��ѯ
	@RequestMapping(value= {"list",""})
	public String list(WorkCell workCell, HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("+++++++++++++++++++++++++\n++++++list1++++++++\n+++++++++++++++++++++++++");
		Page<WorkCell> page=workCellService.findPage(new Page<WorkCell>(request,response), workCell);
		model.addAttribute("page", page);
		System.out.println("+++++++++++++++++++++++++\n++++++list2++++++++\n+++++++++++++++++++++++++");
		return "modules/bas/workCellList";
	}
	//����
	@RequestMapping(value="save")
	public String save(WorkCell workCell, Model model, RedirectAttributes redirectAttributes) {
		workCellService.save(workCell);
		this.addMessage(redirectAttributes, "���湤λ��Ϣ�ɹ�");
		System.out.println("+++++++++++++++++++++++++\n++++++save++++++++\n+++++++++++++++++++++++++");
		return "redirect:"+adminPath+"/bas/workCell";
	}
	//ɾ��
	@RequestMapping(value="delete")
	public String delete(WorkCell workCell, Model model, RedirectAttributes redirectAttributes) {
		workCellService.delete(workCell);
		this.addMessage(redirectAttributes, "ɾ����λ��Ϣ�ɹ�");
		System.out.println("+++++++++++++++++++++++++\n++++++delete++++++++\n+++++++++++++++++++++++++");
		return "redirect:"+adminPath+"/bas/workCell";
	}
	//
	@RequestMapping(value="form")
	public String form(WorkCell workCell, Model model) {
		System.out.println(workCell.getCellName());
		List<WorkStationInfos> workStationInfosList=workStationInfosService.findAllList(new WorkStationInfos());
		model.addAttribute("workStationInfosList", workStationInfosList);
		model.addAttribute("workCell", workCell);
		System.out.println("+++++++++++++++++++++++++\n+++++++form+++++++++\n+++++++++++++++++++++++++");
		return "modules/bas/workCellForm";
	}
}
