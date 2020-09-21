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
import cn.action.modules.bas.entity.Line;
import cn.action.modules.bas.entity.WorkStationInfos;
import cn.action.modules.bas.service.LineService;
import cn.action.modules.bas.service.WorkStationInfosService;

@Controller
@RequestMapping(value="${adminPath}/bas/workStationInfos")
public class WorkStationInfosController extends BaseController{
	@Autowired
	private WorkStationInfosService workStationInfosService;
	@Autowired
	private LineService lineService;
	@ModelAttribute("workStationInfos")
	public WorkStationInfos get(@RequestParam(required=false)String id) {
		if (StringUtils.isNotBlank(id)) {
			return workStationInfosService.get(id);
		}
		return new WorkStationInfos();
	}
	//��ҳ��ѯ
	@RequestMapping(value = {"list",""})
	public String list(WorkStationInfos workStationInfos, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WorkStationInfos> page=workStationInfosService.findPage(new Page<WorkStationInfos>(request,response), workStationInfos);
		model.addAttribute("page", page);
		return "modules/bas/workStationInfosList";
	}
	//����
	@RequestMapping("save")
	public String save(WorkStationInfos workStationInfos, Model model, RedirectAttributes redirectAttributes) {
		workStationInfosService.save(workStationInfos);
		this.addMessage(redirectAttributes, "���湤վ��Ϣ�ɹ���");
		return "redirect:"+adminPath+"/bas/workStationInfos";
	}
	//ɾ��
	@RequestMapping("delete")
	public String delete(WorkStationInfos workStationInfos, Model model, RedirectAttributes redirectAttributes) {
		workStationInfosService.delete(workStationInfos);
		this.addMessage(redirectAttributes, "ɾ����վ��Ϣ�ɹ���");
		return "redirect:"+adminPath+"/bas/workStationInfos";
	}
	@RequestMapping("form")
	public String form(WorkStationInfos workStationInfos, Model model) {
		List<Line> lineList = lineService.findAllList(new Line());
		model.addAttribute("lineList",lineList);
		model.addAttribute("workStationInfos", workStationInfos);
		return "modules/bas/workStationInfosForm";
	}
}
