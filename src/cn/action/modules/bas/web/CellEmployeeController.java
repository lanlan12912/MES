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
import cn.action.modules.bas.entity.CellEmployee;
import cn.action.modules.bas.entity.Employee;
import cn.action.modules.bas.entity.WorkCell;
import cn.action.modules.bas.entity.WorkStationInfos;
import cn.action.modules.bas.service.CellEmployeeService;
import cn.action.modules.bas.service.EmployeeService;
import cn.action.modules.bas.service.WorkCellService;
@Controller
@RequestMapping(value="${adminPath}/bas/cellEmployee")
public class CellEmployeeController extends BaseController{
	@Autowired
	private CellEmployeeService cellEmployeeService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private WorkCellService workCellService;
	@ModelAttribute
	public CellEmployee get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return cellEmployeeService.get(id);
		}
		return new CellEmployee();
	}
	//分页按条件查询
	@RequestMapping(value= {"list",""})
	public String list(CellEmployee cellEmployee, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CellEmployee> page=cellEmployeeService.findPage(new Page<CellEmployee>(request,response), cellEmployee);
		model.addAttribute("page", page);
		return "modules/bas/cellEmployeeList";
	}
	//保存
	@RequestMapping(value="save")
	public String save(CellEmployee cellEmployee, Model model, RedirectAttributes redirectAttributes) {
		String message=null;
		String url=null;
		CellEmployee relation=cellEmployeeService.saveRelation(cellEmployee);
		if (relation==null) {
			message = "保存员工与工位的关系信息成功";
			url = "redirect:"+adminPath+"/bas/cellEmployee";
		}else {
			message = "一名员工只能对应一个工位！"+relation.getEmployee().getEmployeeName()+"已经绑定"+relation.getWorkCell().getCellName();
			url = "redirect:"+adminPath+"/bas/cellEmployee/form";
		}
		this.addMessage(redirectAttributes, message);
		return url;
	}
	//删除
	@RequestMapping(value="delete")
	public String delete(CellEmployee cellEmployee, Model model, RedirectAttributes redirectAttributes) {
		cellEmployeeService.delete(cellEmployee);
		this.addMessage(redirectAttributes, "删除员工与工位的关系信息成功");
		return "redirect:"+adminPath+"/bas/cellEmployee";
	}
	
	@RequestMapping(value="form")
	public String form(CellEmployee cellEmployee, Model model) {
		List<Employee> employeeList=employeeService.findAllList(new Employee());
		List<WorkCell> workCellList=workCellService.findAllList(new WorkCell());
		model.addAttribute("employeeList", employeeList);
		model.addAttribute("workCellList", workCellList);
		model.addAttribute("cellEmployee", cellEmployee);
		return "modules/bas/cellEmployeeForm";
	}

}
