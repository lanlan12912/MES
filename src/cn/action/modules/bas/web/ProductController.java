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
import cn.action.modules.bas.entity.Product;
import cn.action.modules.bas.service.ProductService;
import cn.action.modules.tec.entity.Flow;
import cn.action.modules.tec.service.FlowService;

@Controller
@RequestMapping(value = "${adminPath}/bas/product")
public class ProductController extends BaseController {
	@Autowired
	private ProductService productService;
	@Autowired
	private FlowService flowService;

	@ModelAttribute("product")
	public Product get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return productService.get(id);
		}
		return new Product();
	}

	// ��������ҳ��ѯ
	@RequestMapping(value = { "list", "" })
	public String list(Product product, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Product> page = productService.findPage(new Page<>(request, response), product);
		model.addAttribute("page", page);
		return "modules/bas/productList";
	}

	// ����
	@RequestMapping(value = "save")
	public String save(Product product, RedirectAttributes redirectAttributes, Model model) {
		productService.save(product);
		this.addMessage(redirectAttributes, "�����Ʒ��Ϣ�ɹ���");
		return "redirect:" + adminPath + "/bas/product";
	}

	// ɾ��
	@RequestMapping(value = "delete")
	public String delete(Product product, RedirectAttributes redirectAttributes, Model model) {
		productService.delete(product);
		this.addMessage(redirectAttributes, "ɾ����Ʒ�ɹ���");
		return "redirect:" + adminPath + "/bas/product";
	}

	// ��ת��productForm.jsp
	@RequestMapping(value = "form")
	public String form(Product product, Model model) {
		// ������й�������
		List<Flow> flowList = flowService.findAllList(new Flow());
		model.addAttribute("flowList", flowList);
		model.addAttribute("prduct", product);
		return "modules/bas/productForm";
	}
}
