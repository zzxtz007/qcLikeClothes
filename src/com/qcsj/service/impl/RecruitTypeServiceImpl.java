package com.qcsj.service.impl;

import com.qcsj.dao.DaoFactory;
import com.qcsj.entity.RecruitType;
import com.qcsj.service.RecruitTypeService;
import com.qcsj.service.ServiceUtil.ServiceUtil;
import com.qcsj.service.ServiceUtil.SuperInfo;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecruitTypeServiceImpl implements RecruitTypeService {
	@Override
	public SuperInfo ShowManageType(String pageSize, String pageNum, HttpSession session) {
		SuperInfo si = new SuperInfo();
		int size = 0;
		int num = 0  ;
		// 类型转换异常 3
		try {
			size = Integer.parseInt(pageSize);
			num = Integer.parseInt(pageNum);
		} catch (Exception e) {
			si.setRet(3);
			return si;
		}

		// 未登录时返回 2
		if (!ServiceUtil.isLoggedIn(session)) {
			si.setRet(2);
			return si;
		}
		try {
			ArrayList<RecruitType> list = DaoFactory.getRecruitTypeDao().getRecruitTypeByPage(size, num);
			si.setLists(list);
			// 查询所有type
			si.setRet(0);
			return si;
		} catch (SQLException e) {
			//用户登录时发生异常 1
			si.setRet(1);
			return si;
		}
	}

	@Override
	public SuperInfo getTotalRecruitPage(Integer pageSize, HttpSession session) {
		SuperInfo si = new SuperInfo();

		// 未登录时返回 2
		if (!ServiceUtil.isLoggedIn(session)) {
			si.setRet(2);
			return si;
		}
		try {
			Integer pageNum = DaoFactory.getRecruitTypeDao().getRecruitTypePageNum(5);
			si.setO(pageNum);
			// 查询所有type
			si.setRet(0);
			return si;
		} catch (SQLException e) {
			//用户登录时发生异常 1
			si.setRet(1);
			return si;
		}
	}
}
