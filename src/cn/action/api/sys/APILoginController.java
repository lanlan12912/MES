package cn.action.api.sys;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.action.common.utils.UserUtils;
import cn.action.common.web.BaseController;
import cn.action.common.web.SverResponse;
import cn.action.modules.sys.entity.Menu;
import cn.action.modules.sys.entity.User;
import cn.action.modules.sys.service.SystemService;

@Controller
@RequestMapping("${adminPath}/api")
public class APILoginController extends BaseController {
	
	@Autowired
	private SystemService systemService;
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public SverResponse<User> login(HttpSession session,String account,String pwd){
		User user = systemService.login(account, pwd);
		if (user!=null) {
			user.setRoleList(UserUtils.getRoleList());
			session.setAttribute(UserUtils.CURRENT_USER, user);
			//获取用户的菜单
			List<Menu> menus = systemService.getMenuByUserId(user.getId());
			session.setAttribute(UserUtils.MENU_LIST, menus);
			return SverResponse.createRespBySuccess(user);
		}else {
			return SverResponse.createByErrorMessage("用户名或者密码错误，请重新输入！");
		}
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	@ResponseBody
	public SverResponse<String> logout(HttpSession session){
		User user = (User)session.getAttribute(UserUtils.CURRENT_USER);
		if (user!=null) {
			session.removeAttribute(UserUtils.CURRENT_USER);
			return SverResponse.createRespBySuccess("退出成功！");
		}else return SverResponse.createByErrorMessage("当前未登陆！");
		
	}
}
