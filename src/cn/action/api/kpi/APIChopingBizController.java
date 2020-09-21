package cn.action.api.kpi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.action.common.persistence.Page;
import cn.action.common.utils.UserUtils;
import cn.action.common.web.BaseController;
import cn.action.common.web.SverResponse;
import cn.action.modules.kpi.entity.ChopingBiz;
import cn.action.modules.kpi.service.ChopingBizService;
import cn.action.modules.sys.entity.User;

@Controller
@RequestMapping(value="${adminPath}/api/kpi/chopingBiz")
public class APIChopingBizController extends BaseController {
	
	@Autowired
	private ChopingBizService chopingBizService;
	
	@RequestMapping(value= {"list",""})
	@ResponseBody
	public SverResponse<Page<ChopingBiz>> list(ChopingBiz chopingBiz,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		User user = (User)session.getAttribute(UserUtils.CURRENT_USER);
		if (user!=null) {
			Page<ChopingBiz> page = chopingBizService.findPage(new Page<ChopingBiz>(request, response), chopingBiz); 
			return SverResponse.createRespBySuccess(page);
		}else {
			return SverResponse.createByErrorMessage("µ±Ç°Î´µÇÂ¼£¡ÇëµÇÂ¼ºóÔÙÊÔ£¡");
		}
	}
}
