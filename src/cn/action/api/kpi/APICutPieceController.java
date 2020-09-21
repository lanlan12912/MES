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
import cn.action.modules.kpi.entity.CutPiece;
import cn.action.modules.kpi.service.CutPieceService;
import cn.action.modules.sys.entity.User;

@Controller
@RequestMapping(value="${adminPath}/api/kpi/cutPiece")
public class APICutPieceController extends BaseController{
	
	@Autowired
	private CutPieceService cutPieceService;
	
	@RequestMapping(value= {"list",""})
	@ResponseBody
	public SverResponse<Page<CutPiece>> list(CutPiece cutPiece,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		User user = (User)session.getAttribute(UserUtils.CURRENT_USER);
		if(user != null) {
			Page<CutPiece> page = cutPieceService.findPage(new Page<CutPiece>(request,response), cutPiece);
			return SverResponse.createRespBySuccess(page);
		}else {
			return SverResponse.createByErrorMessage("Î´µÇÂ¼£¡ÇëµÇÂ¼ºóÔÙÊÔ¡£");
		}
		
	}

}
