package cn.action.modules.bas.web;

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
import cn.action.modules.bas.entity.Employee;
import cn.action.modules.bas.service.EmployeeService;
@Controller
@RequestMapping(value="${adminPath}/bas/employee")
public class EmployeeController extends BaseController{
	@Autowired
	private EmployeeService employeeService;
	@ModelAttribute("employee")
	public Employee get(@RequestParam(required=false) String id) {

		System.out.println("get");
		if (StringUtils.isNotBlank(id)) {
			System.out.println("get not null");
			return employeeService.get(id);
		}
		return new Employee();
	}
	//分页按条件查询
	@RequestMapping(value= {"list",""})
	public String list(Employee employee, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Employee> page=employeeService.findPage(new Page<Employee>(request,response), employee);
		model.addAttribute("page", page);
		return "modules/bas/employeeList";
	}
	//保存
	@RequestMapping(value="save")
	public String save(Employee employee, Model model, RedirectAttributes redirectAttributes) {
		try{
			Employee empl = employeeService.get(employee.getEmployeeNo());
			if(empl == null){
				this.addMessage(redirectAttributes, "保存员工信息失败,请检查员工号是否正确");
			}
			else {
				employeeService.save(employee);
				this.addMessage(redirectAttributes, "保存员工信息成功");
			}
		}catch (Exception e ) {
			e.printStackTrace();
		}
		return "redirect:"+adminPath+"/bas/employee";
	}
	//删除
	@RequestMapping(value="delete")
	public String delete(Employee employee, Model model, RedirectAttributes redirectAttributes) {
		employeeService.delete(employee);
		this.addMessage(redirectAttributes, "删除员工信息成功");
		return "redirect:"+adminPath+"/bas/employee";
	}
	//
	@RequestMapping(value="form")
	public String form(Employee employee, Model model) {
		System.out.println(employee.getAddress());
		System.out.println("*****************************************************************");
		model.addAttribute("employee", employee);
		return "modules/bas/employeeForm";
	}
}
