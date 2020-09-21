package cn.action.modules.bas.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.action.modules.bas.entity.WorkCellChange;
import cn.action.modules.bas.service.WorkCellChangeService;

@Controller
@RequestMapping("${adminPath}/bas/workCellChange")
public class WorkCellChangeController {
	@Autowired 
	private WorkCellChangeService workCellChangeService;
	@RequestMapping(value= {""})
	public String init(HttpServletRequest request,HttpServletResponse response, Model model)  {
		List<WorkCellChange> page = workCellChangeService.findAllList(new WorkCellChange());
		List<String> x=new ArrayList<String>();
		for(int i=0;i<page.size();i++)
		{
			x.add(page.get(i).getCellName());
			System.out.println(x.get(i));
		}
		model.addAttribute("WorkCellChange",new WorkCellChange());
		model.addAttribute("WorkCellList",page);
		return "modules/bas/workCellChange";
	}
	@RequestMapping(value= {"form"})
	public String list(WorkCellChange workCellChange,HttpServletRequest request,HttpServletResponse response, Model model) {
		System.out.println(workCellChange.getCellName());
		System.out.println(workCellChange.getYear());
		List<WorkCellChange> pice = workCellChangeService.findAllList(new WorkCellChange());
		List<WorkCellChange> page = workCellChangeService.findList(workCellChange);
		List<WorkCellChange> month=new ArrayList<>();
		for(int i=1;i<=12;i++)
		{
			int yes=0;
			for(int j=0;j<page.size();j++)
			{
				if(page.get(j).getMonth().equals(i+""))
				{
					yes=1;
					month.add(page.get(j));
					break;
				}
			}
			if(yes==0)
			{
				WorkCellChange temp=new WorkCellChange();
				temp.setMonth(i+"");
				temp.setTotalTime(0+"");
				month.add(temp);
			}
		}
		model.addAttribute("WorkCellList",pice);
		model.addAttribute("WorkCellChange",workCellChange);
		model.addAttribute("pic", month);
		return "modules/bas/workCellChange";
	}
}
