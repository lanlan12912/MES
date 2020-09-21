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
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/*import com.alibaba.druid.stat.TableStat.Mode;
import com.drew.lang.StringUtil;*/

import cn.action.common.persistence.Page;
import cn.action.common.utils.StringUtils;
import cn.action.common.web.BaseController;
import cn.action.modules.bas.entity.AndroidPAD;
import cn.action.modules.bas.entity.WorkCell;
import cn.action.modules.bas.entity.WorkStationInfos;
import cn.action.modules.bas.service.AndroidPADService;
import cn.action.modules.bas.service.WorkCellService;
import cn.action.modules.bas.service.WorkStationInfosService;

@Controller
@RequestMapping(value="${adminPath}/bas/androidPAD")
public class AndroidPADController extends BaseController{
	@Autowired
	private AndroidPADService androidPADService;
	@Autowired
	private WorkCellService workCellService;
	@Autowired
	private WorkStationInfosService workStationInfosService;
	
	@ModelAttribute("androidPAD")
	public AndroidPAD get(@RequestParam(required=false) String id) {
		if(StringUtils.isNotBlank(id)) {
			return androidPADService.get(id);
		}
		return new AndroidPAD();
	}
	
	//查询
	@RequestMapping(value= {"list",""})
	public String list(AndroidPAD androidPAD,HttpServletRequest request,HttpServletResponse response,Model model) {
		Page<AndroidPAD> page = androidPADService.findPage(new Page<AndroidPAD>(request,response ), androidPAD);
		model.addAttribute("page",page);
		return "modules/bas/androidPADList";
		
	}
	//保存
	@RequestMapping(value="save")
	public String save(AndroidPAD androidPAD,Model model,RedirectAttributes redirectAttributes) {
		androidPADService.save(androidPAD);
		this.addMessage(redirectAttributes, "保存PAD信息成功！");
		return "redirect:"+adminPath+"/bas/androidPAD";
	}
	//删除
	@RequestMapping(value="delete")
	public String delete(AndroidPAD androidPAD,Model model,RedirectAttributes redirectAttributes) {
		androidPADService.delete(androidPAD);
		this.addMessage(redirectAttributes, "删除PAD信息成功！");
		return "redirect:"+adminPath+"/bas/androidPAD";
	}
	//跳转
	@RequestMapping(value="form")
	public String form(AndroidPAD androidPAD,Model model) {
		//获得所有工位
		List<WorkCell> workCellList = workCellService.findAllList(new WorkCell());
		//获得所有工站
		List<WorkStationInfos> workStationInfosList = workStationInfosService.findAllList(new WorkStationInfos());
		model.addAttribute("workCellList",workCellList);
		model.addAttribute("workStationInfosList",workStationInfosList);
		model.addAttribute("androidPAD",androidPAD);
	
		return "modules/bas/androidPADForm";
	}
	
}
