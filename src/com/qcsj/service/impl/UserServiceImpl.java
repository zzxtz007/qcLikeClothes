package com.qcsj.service.impl;

import com.qcsj.dao.DaoFactory;
import com.qcsj.entity.User;
import com.qcsj.service.ServiceUtil.ServiceUtil;
import com.qcsj.service.ServiceUtil.SuperInfo;
import com.qcsj.service.UserService;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * @author ICE_DOG
 */
public class UserServiceImpl implements UserService {
	/**
	 * session 4小时超时
	 */
	private static final int SESSION_TIMEOUT = 4 * 60 * 60;

	@Override
	public SuperInfo loginManage(String username, String password, HttpSession session) {
		SuperInfo si = new SuperInfo();
		// 参数不完整时返回 1
		if (username == null || password == null) {
			si.setRet(2);
			return si;
		}

		// 已登录时返回 1
		if (ServiceUtil.isLoggedIn(session)) {
			si.setRet(3);
			return si;
		}
		try {
			// 用户名不存在时返回 1
			User foundUser = DaoFactory.getUserDao().queryByUsername(username);
			if (foundUser == null) {
				si.setRet(4);
				return si;
			}

			// 密码错误时返回 1
			String pwd = foundUser.getPassword();
			if (!password.equals(pwd)) {
				si.setRet(5);
				return si;
			}

			// 登录
			session.setMaxInactiveInterval(SESSION_TIMEOUT);
			session.setAttribute("uid", foundUser.getId());
			si.setRet(0);
			return si;
		} catch (SQLException e) {
			//用户登录时发生异常 1
			si.setRet(1);
			return si;
		}
	}
}
