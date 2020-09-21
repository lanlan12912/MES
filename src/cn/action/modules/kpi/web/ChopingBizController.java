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
import cn.action.modules.kpi.entity.ChopingBiz;
import cn.action.modules.kpi.service.ChopingBizService;

@Controller
@RequestMapping(value="${adminPath}/kpi/chopingBiz")
public class ChopingBizController extends BaseController{
	@Autowired
	private ChopingBizService chopingBizService;
	
	@ModelAttribute("chopingBiz")
	public ChopingBiz get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return chopingBizService.get(id);
		}else{
			return new ChopingBiz();
		}
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(ChopingBiz chopingBiz, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<ChopingBiz> page = chopingBizService.findPage(new Page<ChopingBiz>(request, response), chopingBiz); 
        model.addAttribute("page", page);
		return "modules/kpi/chopingBizList";
	}
	
	@RequestMapping(value="month")
	public String exportMonthExcel(ChopingBiz chopingBiz, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ChopingBiz> page = chopingBizService.findPage(new Page<ChopingBiz>(request,response), chopingBiz);
		page.setList(chopingBizService.findMonthList(chopingBiz));
		model.addAttribute("page", page);
		return "modules/kpi/chopingBizMonthList";
	}
	
	@RequestMapping(value= "export")
	public String exportExcel(ChopingBiz chopingBiz,HttpServletResponse response,RedirectAttributes redirectAttributes,String isMonth) {
		String message = "报表导出失败！";
		try {
			String title = "";
			List<ChopingBiz> chopingBizs = null;
			if(isMonth!=null && isMonth.equals("1")) {
				title = "切片绩效月度报表";
				chopingBizs = chopingBizService.findMonthList(new ChopingBiz());
			}
			else {
				title = "切片绩效日度报表";
				chopingBizs = chopingBizService.findAllList(new ChopingBiz());
			}
			
	        String[] rowsName = new String[]{"序号", "日期", "人员", "总数", "薪资"};
	        List<Object[]> dataList = new ArrayList<Object[]>();
	        Object[] objs = null;
	        for (int i = 0; i < chopingBizs.size(); i++) {
	            objs = new Object[rowsName.length];
	            objs[0] = i;
	            objs[1] = chopingBizs.get(i).getTime();
	            objs[2] = chopingBizs.get(i).getEmployeeName();
	            objs[3] = chopingBizs.get(i).getPieces();
	            objs[4] = chopingBizs.get(i).getMoney();
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
		
       
		return "redirect:"+adminPath+"/kpi/chopingBiz";
	}

}

