package cn.action.modules.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Maps;

import cn.action.common.config.Global;
import cn.action.common.utils.CookieUtils;
import cn.action.common.utils.EhCacheUtils;
import cn.action.common.utils.StringUtils;
import cn.action.common.utils.UserUtils;
import cn.action.common.web.BaseController;
import cn.action.common.web.Response;
import cn.action.common.web.SverResponse;
import cn.action.modules.sys.entity.Menu;
import cn.action.modules.sys.entity.User;
import cn.action.modules.sys.service.SystemService;

@Controller
public class LoginController extends BaseController {

	@Autowired
	private SystemService systemService;

	/**
	 * 管理登录
	 */
	@RequestMapping(value = "${adminPath}/login", method = RequestMethod.GET)
	public String login(HttpSession session, HttpServletResponse response, Model model) {

		// 如果已登录，再次访问主页，则退出原账号。
		if (Global.TRUE.equals(Global.getConfig("notAllowRefreshIndex"))) {
			CookieUtils.setCookie(response, "LOGINED", "false");
		}
		User user = (User) session.getAttribute(UserUtils.CURRENT_USER);
		// 如果已经登录，则跳转到管理首页
		if (user != null) {
			return "redirect:" + adminPath;
		}
		return "modules/sys/sysLogin";
	}

	/***
	 * 登录返回json数据
	 * 
	 * @param session
	 * @param response
	 * @param request
	 * @param account
	 * @param pwd
	 * @param model
	 * @param accept
	 * @return
	 */

	@RequestMapping(value = "${adminPath}/login", method = RequestMethod.POST)
	public String login(HttpSession session, HttpServletResponse response, HttpServletRequest request, String account,
			String pwd, Model model, @RequestHeader("Accept") String accept) {
		User user = systemService.login(account, pwd);
		if (user != null) {
			user.setRoleList(UserUtils.getRoleList());
			session.setAttribute(UserUtils.CURRENT_USER, user);
			// 获取用户的菜单
			List<Menu> menus = systemService.getMenuByUserId(user.getId());
			session.setAttribute(UserUtils.MENU_LIST, menus);
			String json = renderString(response, SverResponse.createRespBySuccess(user));
			String url = "modules/sys/sysIndex";
			return Response.CreateResponse(accept, json, url);
		} else {
			String json = renderString(response, SverResponse.createByErrorMessage("用户名或者密码错误，请重新输入！"));
			String url = "redirect:" + adminPath;
			return Response.CreateResponse(accept, json, url);
		}
	}

	/**
	 * 登录成功，进入管理首页
	 */
	@RequestMapping(value = "${adminPath}")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		User principal = UserUtils.getUser();

		// 登录成功后，验证码计算器清零
		// isValidateCodeLogin(principal.getLoginName(), false, true);
		if (principal != null && principal.getId() == null) {
			return "redirect:" + adminPath + "/login";
		}
		return "modules/sys/sysIndex";
	}

	/**
	 * 获取主题方案
	 */
	@RequestMapping(value = "/theme/{theme}")
	public String getThemeInCookie(@PathVariable String theme, HttpServletRequest request,
			HttpServletResponse response) {
		if (StringUtils.isNotBlank(theme)) {
			CookieUtils.setCookie(response, "theme", theme);
		} else {
			theme = CookieUtils.getCookie(request, "theme");
		}
		return "redirect:" + request.getParameter("url");
	}

	/**
	 * 是否是验证码登录
	 * 
	 * @param useruame
	 *            用户名
	 * @param isFail
	 *            计数加1
	 * @param clean
	 *            计数清零
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean isValidateCodeLogin(String useruame, boolean isFail, boolean clean) {
		Map<String, Integer> loginFailMap = (Map<String, Integer>) EhCacheUtils.get("loginFailMap");
		if (loginFailMap == null) {
			loginFailMap = Maps.newHashMap();
			EhCacheUtils.put("loginFailMap", loginFailMap);
		}
		Integer loginFailNum = loginFailMap.get(useruame);
		if (loginFailNum == null) {
			loginFailNum = 0;
		}
		if (isFail) {
			loginFailNum++;
			loginFailMap.put(useruame, loginFailNum);
		}
		if (clean) {
			loginFailMap.remove(useruame);
		}
		return loginFailNum >= 3;
	}

	@RequestMapping(value = "${adminPath}/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, @RequestHeader("Accept") String accept, HttpServletResponse response) {
		session.removeAttribute(UserUtils.CURRENT_USER);
		String url = "redirect:" + adminPath;
		String json = renderString(response, SverResponse.createRespBySuccessMessage("退出成功!"));
		return Response.CreateResponse(accept, json, url);
	}
}
