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
import cn.action.modules.kpi.entity.CutPiece;
import cn.action.modules.kpi.service.CutPieceService;

@Controller
@RequestMapping(value="${adminPath}/kpi/cutPiece")
public class CutPieceController extends BaseController{
	@Autowired
	private CutPieceService cutPieceService;
	
	@ModelAttribute("cutPiece")
	public CutPiece get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return cutPieceService.get(id);
		}else{
			return new CutPiece();
		}
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(CutPiece cutPiece, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<CutPiece> page = cutPieceService.findPage(new Page<CutPiece>(request, response), cutPiece); 
        model.addAttribute("page", page);
		return "modules/kpi/cutPieceList";
	}

	@RequestMapping(value="month")
	public String exportMonthExcel(CutPiece cutPiece, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CutPiece> page = cutPieceService.findPage(new Page<CutPiece>(request, response), cutPiece); 
		page.setList(cutPieceService.findMonthList(cutPiece));
		model.addAttribute("page", page);
		return "modules/kpi/cutPieceMonthList";
	}
	
	@RequestMapping(value= "export")
	public String exportExcel(CutPiece cutPiece,HttpServletResponse response,RedirectAttributes redirectAttributes,String isMonth) {
		String message = "报表导出失败！";
		try {
			String title = "切片绩效日度报表";
	        List<CutPiece> cutPieces = cutPieceService.findAllList(new CutPiece());
	        if(isMonth != null && isMonth.equals("1")) {
	        	title = "切片绩效月度报表";
	        	cutPieces = cutPieceService.findMonthList(new CutPiece());
	        }
	        String[] rowsName = new String[]{"序号", "日期", "人员", "总数", "薪资"};
	        List<Object[]> dataList = new ArrayList<Object[]>();
	        Object[] objs = null;
	        for (int i = 0; i < cutPieces.size(); i++) {
	            objs = new Object[rowsName.length];
	            objs[0] = i;
	            objs[1] = cutPieces.get(i).getTime();
	            objs[2] = cutPieces.get(i).getEmployeeName();
	            objs[3] = cutPieces.get(i).getCounter();
	            objs[4] = cutPieces.get(i).getMoney();
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
		
       
		return "redirect:"+adminPath+"/kpi/cutPieceList";
	}
}

