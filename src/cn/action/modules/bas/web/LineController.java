package cn.action.modules.bas.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.action.common.config.Global;
import cn.action.common.persistence.Page;
import cn.action.common.utils.StringUtils;
import cn.action.common.web.BaseController;
import cn.action.modules.bas.entity.Line;
import cn.action.modules.bas.entity.WorkShop;
import cn.action.modules.bas.entity.WorkStationInfos;
import cn.action.modules.bas.service.LineService;
import cn.action.modules.bas.service.WorkShopService;

@Controller
@RequestMapping(value = "${adminPath}/bas/line")
public class LineController  extends BaseController{
	@Autowired
	private LineService lineService;
	@Autowired
	private WorkShopService workShopService;
	@ModelAttribute("line")
	public Line get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			System.out.println("run.....Line");
			return lineService.get(id);
		}else{
			return new Line();
		}
	}
	@RequestMapping(value = {"list", ""})
	public String list(Line line, HttpServletRequest request, HttpServletResponse response, Model model) {
		
        Page<Line> page = lineService.findPage(new Page<Line>(request, response), line); 
        model.addAttribute("page", page);
		return "modules/bas/lineList";
	}


	@RequestMapping(value = "form")
	public String form(Line line, Model model) {
		System.out.println(line+line.getId());
		//model.addAttribute("iPhoto", iPhoto);
		model.addAttribute("shopList", workShopService.findAllList(new WorkShop()));
		return "modules/bas/lineForm";
	}


	@RequestMapping(value = "save")//@Valid 
	public String save(Line line, Model model, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/bas/line/?repage&qrCode="+line.getLineNumber();
		}
		
		lineService.save(line);
		addMessage(redirectAttributes, "保存产线'" + line.getLineNumber() + "'成功");
		return "redirect:" + adminPath + "/bas/line/?repage&qrCode="+line.getLineNumber();
	}
	

	@RequestMapping(value = "delete")
	public String delete(Line line, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/bas/line/?repage";
		}
		lineService.delete(line);
		addMessage(redirectAttributes, "删除产线成功");
		return "redirect:" + adminPath + "/bas/line/?repage&type="+line.getLineNumber();
	}

	
	@ResponseBody
	@RequestMapping(value = "listData")
	public List<Line> listData(@RequestParam(required=false) String lineName) {
		Line line = new Line();
		line.setLineName(lineName);
		return lineService.findList(line);
	}
}
