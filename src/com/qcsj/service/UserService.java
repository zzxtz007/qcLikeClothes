package com.qcsj.service;

import com.qcsj.service.ServiceUtil.SuperInfo;

import javax.servlet.http.HttpSession;

public interface UserService {
	SuperInfo loginManage(String username, String password, HttpSession session);
}
