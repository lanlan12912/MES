package cn.action.modules.bas.web;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.action.common.persistence.Page;
import cn.action.common.web.BaseController;
import cn.action.modules.bas.entity.EmployeeTime;
import cn.action.modules.bas.entity.WorkCellTime;
import cn.action.modules.bas.service.EmployeeTimeService;
import cn.action.modules.bas.service.WorkCellTimeService;
@Controller
@RequestMapping("${adminPath}/bas/workCellTime")
public class WorkCellTimeController {
	@Autowired 
	private WorkCellTimeService workCellTimeService;
	
	@RequestMapping(value= {""})
	public String init(HttpServletRequest request,HttpServletResponse response, Model model)  {
		WorkCellTime temp=new WorkCellTime();
		model.addAttribute("WorkCellTime",temp);
		return "modules/bas/workCellTime";
	}
		@RequestMapping(value= {"form"})
		public String list(WorkCellTime workCellTime,HttpServletRequest request,HttpServletResponse response, Model model) {
			List<WorkCellTime> page = workCellTimeService.findList(workCellTime);
			model.addAttribute("WorkCellTime",workCellTime);
			model.addAttribute("pic", page);
			return "modules/bas/workCellTime";
		}
		public int getDaysByYearMonth(int year, int month) {
			Calendar a = Calendar.getInstance();
			a.set(Calendar.YEAR, year);
			a.set(Calendar.MONTH, month - 1);
			a.set(Calendar.DATE, 1);
			a.roll(Calendar.DATE, -1);
			int maxDate = a.get(Calendar.DATE);
			return maxDate;
		}
}
