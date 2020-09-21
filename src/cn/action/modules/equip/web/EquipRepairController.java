package cn.action.modules.equip.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.action.common.utils.UserUtils;
import cn.action.common.web.BaseController;
import cn.action.common.web.Response;
import cn.action.common.web.SverResponse;
import cn.action.modules.equip.entity.EquipRepair;
import cn.action.modules.equip.service.EquipFaultReportService;
import cn.action.modules.equip.service.EquipRepairService;
@Controller
@RequestMapping(value="${adminPath}/equip/repair")
public class EquipRepairController extends BaseController{
	@Autowired
	private EquipRepairService equipRepairService;
	@Autowired
	private EquipFaultReportService equipFaultReportService;
	@ModelAttribute("equipRepair")
	public EquipRepair get(@RequestParam(required=false) String id, String mid) {
		if (StringUtils.isNotBlank(id)) {
			return equipRepairService.get(id);
		}
		EquipRepair repair = new EquipRepair();
		repair.setMid(mid);
		return repair;
	}
	//����
	@RequestMapping("save")
	public String save(EquipRepair equipRepair,Model model,RedirectAttributes redirectAttributes,@RequestParam("img") MultipartFile file,HttpServletRequest request) throws IllegalStateException, IOException {
		//�޸��ϱ���¼��״̬Ϊ���깤��������Ҫ����ά�ޱ���		
		String filePath="C:\\Users\\27088\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\action3\\static\\imgs\\"+equipRepair.getMid()+".jpg";
		file.transferTo(new File(filePath));
		equipRepairService.saveRepair(equipRepair);
		this.addMessage(redirectAttributes, "ά�ޱ�����ӳɹ�!");
		return "redirect:"+adminPath+"/equip/report/repairList";
	}
	//��ת��ά�ޱ���ҳ��
	@RequestMapping("form")
	public String form(HttpServletRequest request, HttpServletResponse response, EquipRepair equipRepair, Model model) {
		//������в�����Ϣ
		model.addAttribute("equipRepair", equipRepair);
		
		String accept = request.getHeader("accept");// ��request��ȡaccept������Ҳ����@RequestHeaderע��ȡ������
		String json = renderString(response, SverResponse.createRespBySuccess(equipRepair));// ת����json�ַ���
		String url = "modules/equip/maintenanceReportForm";// Ҫ���ص�url
		return Response.CreateResponse(accept, json, url);// ����accept��������json��url�ַ���
//		return "modules/equip/maintenanceReportForm";
	}
}
