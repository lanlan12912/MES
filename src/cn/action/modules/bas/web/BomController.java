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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.action.common.persistence.Page;
import cn.action.common.utils.StringUtils;
import cn.action.common.web.BaseController;
import cn.action.modules.bas.entity.Bom;
import cn.action.modules.bas.entity.Product;
import cn.action.modules.bas.service.BomService;
import cn.action.modules.bas.service.ProductService;
import cn.action.modules.tec.entity.Flow;
import cn.action.modules.tec.service.FlowService;
@Controller
@RequestMapping(value="${adminPath}/bas/bom")
public class BomController extends BaseController{
	@Autowired
	private BomService bomService;
	@Autowired
	private ProductService productService;
	//根据id获bom
	@ModelAttribute("bom")
	public Bom get(@RequestParam(required=false) String id){
		if(StringUtils.isNotBlank(id)) {
			return bomService.get(id);
		}
		return new Bom();
	}
	//根据条件分页查询
	@RequestMapping(value={"list",""})
	public String list(Bom bom ,HttpServletRequest request,HttpServletResponse response,Model model) {
		Page<Bom> page=bomService.findPage(new Page<Bom>(request,response),bom);
		model.addAttribute("page",page);
		return "modules/bas/bomList";
	}
		
	//保存
	@RequestMapping(value="save")
	public String save(Bom bom ,RedirectAttributes redirectAttributes,Model model) {
		bomService.save(bom);
		this.addMessage(redirectAttributes, "保存信息成功");
		return "redirect:"+adminPath+"/bas/bom";
	}
	//删除
	@RequestMapping(value="delete")
	public String delete(Bom bom ,RedirectAttributes redirectAttributes,Model model) {
		bomService.delete(bom);
		this.addMessage(redirectAttributes, "删除BOM成功");
		return "redirect:"+adminPath+"/bas/bom";
	}
	
	//跳转
	@RequestMapping(value="form")
	public String form(Bom bom,Model model){
		//获得所有的产品信息
		List<Product> products=productService.findAllList(new Product());
		model.addAttribute("productList",products);
		model.addAttribute("Bom", bom);
		return "modules/bas/bomForm";
	}
}
