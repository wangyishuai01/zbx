package com.cn.zbx.controller;

/**
 * @Probject Name: zbx
 * @Create By zhangdl
 * @Create In 2017-10-01
 * TODO
 */

import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cn.zbx.pojo.BUser;
import com.cn.zbx.pojo.Menu;
import com.cn.zbx.service.IBUserLimitService;
import com.cn.zbx.service.IBUserService;
import com.cn.zbx.util.CookiesUtil;
import com.cn.zbx.util.JsonUtil;

/**
 * @Class Name SecutityController
 * @Author zhangdl
 * @Create In 2017-10-01
 */
@Controller
@RequestMapping(value = "/security")
public class SecutityController {

	protected final Log logger = LogFactory.getLog(SecutityController.class);

	@Autowired
	@Qualifier("bUserService")
	private IBUserService bUserService;
	
	@Autowired
	@Qualifier("bUserLimitService")
	private IBUserLimitService bUserLimitService;

	@RequestMapping(value = { "/login" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String login(Model m, HttpServletRequest request, HttpServletResponse response)
			throws SQLException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			BUser u = new BUser();
			u = bUserService.queryUserByName(username);// 根据用户名查询用户是否存在
			if (u != null) {
				logger.info(u.getPassword());
				if (!u.getPassword().equals(password)) {
					request.setAttribute("error", "用户名或密码输入错误，请核对后重新输入。");
					return "forward:/login.jsp";
				}
				List<Menu> menuList = bUserLimitService.queryUserMenuByUser(u);
				if (menuList != null && menuList.size() != 0) {
					CookiesUtil.setCookies(response, "username", username, 60*30);
					CookiesUtil.setCookies(response, "LOne", URLEncoder.encode(JsonUtil.listToJson(menuList), "UTF-8"), 60*120);
					return "redirect:/index.jsp";
				} else {
					request.setAttribute("error", "该用户无权限！");
					return "forward:/login.jsp";
				}
			} else {
				request.setAttribute("error", "用户名或密码输入错误，请核对后重新输入。");
				return "forward:/login.jsp";
			}
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", "系统异常！");
			return "forward:/login.jsp";
		}
	}
	
	@RequestMapping(value = { "/logoutAction" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String logout_v1(Model m, HttpServletRequest request, HttpServletResponse response) {
		CookiesUtil.delAllCookies(request, response);
		return "redirect:/login.jsp";
	}

	public static List getDTOList(String jsonString, Class clazz, Map map) {

		JSONArray array = JSONArray.fromObject(jsonString);
		List list = new ArrayList();
		for (Iterator iter = array.iterator(); iter.hasNext();) {
			JSONObject jsonObject = (JSONObject) iter.next();
			list.add(JSONObject.toBean(jsonObject, clazz, map));
		}
		return list;
	}
}
