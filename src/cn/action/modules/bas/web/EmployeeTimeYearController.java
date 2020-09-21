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
import cn.action.modules.bas.service.EmployeeTimeYearService;
@Controller
@RequestMapping("${adminPath}/bas/employeeTimeYear")
public class EmployeeTimeYearController extends BaseController{
	@Autowired 
	private EmployeeTimeYearService employeeTimeYearService;
	
	//分页按条件查询  
		@RequestMapping(value= {"list",""})
		public String list(EmployeeTime employeeTime,HttpServletRequest request,HttpServletResponse response, Model model) {
			Page<EmployeeTime> page = employeeTimeYearService.findPage(new Page<EmployeeTime>(request,response),employeeTime);
			model.addAttribute("page",page);
			return "modules/bas/employeeTimeYear";		
		} 
}
