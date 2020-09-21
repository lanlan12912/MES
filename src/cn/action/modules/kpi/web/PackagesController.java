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
import cn.action.modules.kpi.entity.Packages;
import cn.action.modules.kpi.service.PackagesService;

@Controller
@RequestMapping(value="${adminPath}/kpi/packages")
public class PackagesController extends BaseController{
	@Autowired
	private PackagesService packageService;
	
	@ModelAttribute("package")
	public Packages get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return packageService.get(id);
		}else{
			return new Packages();
		}
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Packages packages, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Packages> page = packageService.findPage(new Page<Packages>(request, response), packages); 
        model.addAttribute("page", page);
		return "modules/kpi/packagesList";
	}

}

