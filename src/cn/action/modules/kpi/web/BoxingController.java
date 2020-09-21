package cn.action.modules.kpi.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.action.common.persistence.Page;
import cn.action.common.utils.StringUtils;
import cn.action.common.web.BaseController;
import cn.action.modules.kpi.entity.Boxing;
import cn.action.modules.kpi.service.BoxingService;

@Controller
@RequestMapping(value="${adminPath}/kpi/boxing")
public class BoxingController extends BaseController{
	@Autowired
	private BoxingService boxingervice;
	
	@ModelAttribute("package")
	public Boxing get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return boxingervice.get(id);
		}else{
			return new Boxing();
		}
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Boxing boxing, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Boxing> page = boxingervice.findPage(new Page<Boxing>(request, response), boxing); 
        model.addAttribute("page", page);
		return "modules/kpi/boxingList";
	}

}

