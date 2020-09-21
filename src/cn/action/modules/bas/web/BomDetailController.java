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
import cn.action.modules.bas.entity.BomDetail;
import cn.action.modules.bas.service.BomDetailService;
import cn.action.modules.bas.service.BomService;
@Controller
@RequestMapping(value="${adminPath}/bas/bomDetail")
public class BomDetailController extends BaseController{
	@Autowired
	private BomDetailService bomDetailService;
	@Autowired
	private BomService bomservice;
	//����id��bom
		@ModelAttribute("bomDetail")
		public BomDetail get(@RequestParam(required=false) String id){
			if(StringUtils.isNotBlank(id)) {
				return bomDetailService.get(id);
			}
			return new BomDetail();
		}
	//����������ҳ��ѯ
		@RequestMapping(value={"list",""})
		public String list(BomDetail bomDetail ,HttpServletRequest request,HttpServletResponse response,Model model) {
			Page<BomDetail> page=bomDetailService.findPage(new Page<BomDetail>(request,response),bomDetail);
			model.addAttribute("page",page);
			return "modules/bas/bomDetailList";
		}
	//����
		@RequestMapping(value="save")
		public String save(BomDetail bomDetail ,RedirectAttributes redirectAttributes,Model model) {
			bomDetailService.save(bomDetail);
			this.addMessage(redirectAttributes, "������Ϣ�ɹ�");
			return "redirect:"+adminPath+"/bas/bomDetail";
		}
		
	//ɾ��
		@RequestMapping(value="delete")
		public String delete(BomDetail bomDetail ,RedirectAttributes redirectAttributes,Model model) {
			bomDetailService.delete(bomDetail);
			this.addMessage(redirectAttributes, "ɾ��BOM�ɹ�");
			return "redirect:"+adminPath+"/bas/bomDetail";
		}
	//��ת
		@RequestMapping(value="form")
		public String form(BomDetail bomDetail,Model model){
			//������е�Bom��Ϣ
			List<Bom> bomlist=bomservice.findAllList(new Bom());
			model.addAttribute("bomList",bomlist);
			model.addAttribute("bomDetail", bomDetail);
			return "modules/bas/bomDetailForm";
		}
}
