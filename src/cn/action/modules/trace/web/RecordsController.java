package cn.action.modules.trace.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.action.common.persistence.Page;
import cn.action.common.web.BaseController;
import cn.action.modules.trace.entity.Records;
import cn.action.modules.trace.service.RecordsService;

@Controller
@RequestMapping(value = "${adminPath}/trace/traceWorkOrder")
public class RecordsController extends BaseController{
	@Autowired
	private RecordsService recordsService;
	
	//根据条件分页查询
	@RequestMapping(value= {"list",""})
	public String list(Records records,HttpServletRequest request,HttpServletResponse response,Model model) {
		Page<Records> page = recordsService.findPage(new Page<Records>(request,response), records);
		model.addAttribute("page", page);
		return "modules/trace/traceWorkOrder";
	}
}
