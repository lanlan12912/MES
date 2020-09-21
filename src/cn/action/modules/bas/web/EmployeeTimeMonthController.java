package cn.action.modules.bas.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.action.common.persistence.Page;
import cn.action.common.web.BaseController;
import cn.action.modules.bas.entity.EmployeeTime;
import cn.action.modules.bas.service.EmployeeTimeMonthService;
@Controller
@RequestMapping("${adminPath}/bas/employeeTimeMonth")
public class EmployeeTimeMonthController extends BaseController{
	@Autowired 
	private EmployeeTimeMonthService employeeTimeMonthService;
	
	//分页按条件查询  
		@RequestMapping(value= {"list",""})
		public String list(EmployeeTime employeeTime,HttpServletRequest request,HttpServletResponse response, Model model) {
			System.out.println("*********>>>>>>>>");
			Page<EmployeeTime> page = employeeTimeMonthService.findPage(new Page<EmployeeTime>(request,response),employeeTime);
			System.out.println("*********>>>>>>>>"+page.getList().get(0).getMonth());
			System.out.println("*********>>>>>>>>"+page.getList().get(0).getWeek());
			System.out.println("*********>>>>>>>>"+page.getList().get(0).getYear());
			model.addAttribute("page",page);
			return "modules/bas/employeeTimeMonth";		
		} 
}
