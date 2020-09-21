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
import cn.action.modules.kpi.entity.RemoveFishBone;
import cn.action.modules.kpi.service.RemoveFishBoneService;

@Controller
@RequestMapping(value="${adminPath}/kpi/removeFishBone")
public class RemoveFishBoneController extends BaseController{
	@Autowired
	private RemoveFishBoneService removeFishBoneService;
	
	@ModelAttribute("removeFishBone")
	public RemoveFishBone get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return removeFishBoneService.get(id);
		}else{
			return new RemoveFishBone();
		}
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(RemoveFishBone removeFishBone, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<RemoveFishBone> page = removeFishBoneService.findPage(new Page<RemoveFishBone>(request, response), removeFishBone); 
        model.addAttribute("page", page);
		return "modules/kpi/removeFishBoneList";
	}

	@RequestMapping(value="month")
	public String ExportMonthExcel(RemoveFishBone removeFishBone, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RemoveFishBone> page = removeFishBoneService.findPage(new Page<RemoveFishBone>(request, response), removeFishBone); 
		page.setList(removeFishBoneService.findMonthList(new RemoveFishBone())); 
        model.addAttribute("page", page);
		return "modules/kpi/removeFishBoneMonthList";
	}
	
	@RequestMapping(value= "export")
	public String exportExcel(RemoveFishBone removeFishBone,HttpServletResponse response,RedirectAttributes redirectAttributes,String isMonth) {
		String message = "报表导出失败！";
		try {
			String title = "切片绩效日度报表";
	        String[] rowsName = new String[]{"序号", "日期", "人员", "总数", "薪资"};
	        List<RemoveFishBone> removeFishBones = removeFishBoneService.findAllList(new RemoveFishBone());
	        if(isMonth != null && isMonth.equals("1")) {
	        	title = "切片绩效月度报表";
	        	removeFishBones = removeFishBoneService.findMonthList(new RemoveFishBone());
	        }
	        List<Object[]> dataList = new ArrayList<Object[]>();
	        Object[] objs = null;
	        for (int i = 0; i < removeFishBones.size(); i++) {
	            objs = new Object[rowsName.length];
	            objs[0] = i;
	            objs[1] = removeFishBones.get(i).getTime();
	            objs[2] = removeFishBones.get(i).getEmployeeName();
	            objs[3] = removeFishBones.get(i).getWeight();
	            objs[4] = removeFishBones.get(i).getMoney();
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
		
       
		return "redirect:"+adminPath+"/kpi/removeFishBone";
	}
}

