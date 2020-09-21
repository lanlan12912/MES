package cn.action.modules.bas.web;

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
import cn.action.modules.bas.entity.Material;
import cn.action.modules.bas.service.MaterialService;

@Controller
@RequestMapping(value = "${adminPath}/bas/material")
public class MaterialController extends BaseController {
	@Autowired
	private MaterialService materialService;

	@ModelAttribute("material")
	public Material get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return materialService.get(id);
		}
		return new Material();
	}

	// 分页查询
	@RequestMapping(value = { "list", "" })
	public String list(Material material, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Material> page = materialService.findPage(new Page<Material>(request, response), material);
		model.addAttribute("page", page);
		return "modules/bas/materialList";// 跳转到materialList.jsp页面
	}
}
