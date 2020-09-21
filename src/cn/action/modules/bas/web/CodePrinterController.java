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
import cn.action.modules.bas.entity.CodePrinter;
import cn.action.modules.bas.entity.WorkCell;
import cn.action.modules.bas.entity.WorkStationInfos;
import cn.action.modules.bas.service.CodePrinterService;
import cn.action.modules.bas.service.WorkCellService;
import cn.action.modules.bas.service.WorkStationInfosService;

@Controller
@RequestMapping(value="${adminPath}/bas/codePrinter")
public class CodePrinterController extends BaseController{

	@Autowired
	private CodePrinterService codePrinterService;
	@Autowired
	private WorkCellService workCellService;
	@Autowired
	private WorkStationInfosService workStationInfosService;
	
	@ModelAttribute("codePrinter")
	public CodePrinter get(@RequestParam(required=false) String id) {
		if(StringUtils.isNotBlank(id)) {
			return codePrinterService.get(id);
		}
		return new CodePrinter();
	}
	
	//��ѯ
	@RequestMapping(value= {"list",""})
	public String list(CodePrinter codePrinter,HttpServletResponse response,HttpServletRequest request,Model model) {
		Page<CodePrinter> page = codePrinterService.findPage(new Page<CodePrinter>(request,response), codePrinter);
		model.addAttribute("page", page);
		return "modules/bas/codePrinterList";
	}
	//����
	@RequestMapping(value="save")
	public String save(CodePrinter codePrinter,Model model,RedirectAttributes redirectAttributes) {
		codePrinterService.save(codePrinter);
		this.addMessage(redirectAttributes, "���������ӡ���ɹ���");
		return "redirect:"+adminPath+"/bas/codePrinter";
	}
	
	//ɾ��
	@RequestMapping(value="delete")
	public String delete(CodePrinter codePrinter,Model model,RedirectAttributes redirectAttributes) {
		codePrinterService.delete(codePrinter);
		this.addMessage(redirectAttributes, "ɾ�������ӡ���ɹ���");
		return "redirect:"+adminPath+"/bas/codePrinter";
	}
	//��ת
	@RequestMapping(value="form")
	public String form(CodePrinter codePrinter,Model model) {
		//������й�λ
		List<WorkCell> workCellList = workCellService.findAllList(new WorkCell());
		//������й�վ
		List<WorkStationInfos> workStationInfosList = workStationInfosService.findAllList(new WorkStationInfos());
		model.addAttribute("workCellList",workCellList);
		model.addAttribute("workStationInfosList",workStationInfosList);
		model.addAttribute("codePrinter",codePrinter);
	
		return "modules/bas/codePrinterForm";
	}
}
