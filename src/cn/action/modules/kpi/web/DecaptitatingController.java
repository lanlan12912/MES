package cn.action.modules.kpi.web;

import java.util.ArrayList;
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
import cn.action.common.utils.ExportExcel;
import cn.action.common.utils.StringUtils;
import cn.action.common.web.BaseController;
import cn.action.modules.kpi.entity.Decaptitating;
import cn.action.modules.kpi.service.DecaptitatingService;

@Controller
@RequestMapping(value="${adminPath}/kpi/decaptitating")
public class DecaptitatingController extends BaseController{
	@Autowired
	private DecaptitatingService decaptitatingService;
	
	@ModelAttribute("decaptitating")
	public Decaptitating get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return decaptitatingService.get(id);
		}else{
			return new Decaptitating();
		}
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Decaptitating decaptitating, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Decaptitating> page = decaptitatingService.findPage(new Page<Decaptitating>(request, response), decaptitating); 
        model.addAttribute("page", page);
		return "modules/kpi/decaptitatingList";
	}
	
	@RequestMapping(value="month")
	public String exportMonthExcel(Decaptitating decaptitating, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Decaptitating> page = decaptitatingService.findPage(new Page<Decaptitating>(request, response), decaptitating); 
		page.setList(decaptitatingService.findMonthList(decaptitating));
        model.addAttribute("page", page);
		return "modules/kpi/decaptitatingMonthList";
	}
	
	@RequestMapping(value= "export")
	public String exportExcel(Decaptitating decaptitating,HttpServletResponse response,RedirectAttributes redirectAttributes,String isMonth) {
		String message = "报表导出失败！";
		try {
			String title = "切片绩效日度报表";
	        String[] rowsName = new String[]{"序号", "日期", "人员", "总数", "薪资"};
	        List<Decaptitating> decaptitatings = decaptitatingService.findAllList(new Decaptitating());
	        if(isMonth != null && isMonth.equals("1")) {
	        	title = "切片绩效月度报表";
	        	decaptitatings = decaptitatingService.findMonthList(new Decaptitating());
	        }
	        List<Object[]> dataList = new ArrayList<Object[]>();
	        Object[] objs = null;
	        for (int i = 0; i < decaptitatings.size(); i++) {
	            objs = new Object[rowsName.length];
	            objs[0] = i;
	            objs[1] = decaptitatings.get(i).getTime();
	            objs[2] = decaptitatings.get(i).getEmployeeName();
	            objs[3] = decaptitatings.get(i).getWeight();
	            objs[4] = decaptitatings.get(i).getMoney();
	            dataList.add(objs);
	        }
	        ExportExcel ex = new ExportExcel(title, rowsName, dataList);
            if(ex.export(response)) {
            	return null;
            }
		} catch (Exception e) {
			// TODO: handle exception
			this.addMessage(redirectAttributes, message);
		}
		
       
		return "redirect:"+adminPath+"/kpi/decaptitating";
	}

}

