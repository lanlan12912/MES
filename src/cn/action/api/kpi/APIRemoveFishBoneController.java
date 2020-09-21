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
import cn.action.modules.kpi.entity.RemoveFishBone;
import cn.action.modules.kpi.service.RemoveFishBoneService;
import cn.action.modules.sys.entity.User;

@Controller
@RequestMapping(value="${adminPath}/api/kpi/removeFishBone")
public class APIRemoveFishBoneController extends BaseController{

	@Autowired
	private RemoveFishBoneService removeFishBoneService;
	
	@RequestMapping(value= {"list",""})
	@ResponseBody
	public SverResponse<Page<RemoveFishBone>> list(RemoveFishBone removeFishBone,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		User user = (User)session.getAttribute(UserUtils.CURRENT_USER);
		
		if(user != null) {
			Page<RemoveFishBone> page = removeFishBoneService.findPage(new Page<RemoveFishBone>(request,response),removeFishBone);
			return SverResponse.createRespBySuccess(page);
		}else {
			return SverResponse.createByErrorMessage("Î´µÇÂ¼£¡ÇëµÇÂ¼ºóÔÙÊÔ¡£");
		}
	}
	
}
