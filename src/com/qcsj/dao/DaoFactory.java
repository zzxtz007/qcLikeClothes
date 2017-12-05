package com.qcsj.dao;

import com.qcsj.dao.impl.RecruitTypeDaoImpl;
import com.qcsj.dao.impl.UserDaoImpl;

public class DaoFactory {
	public static UserDao getUserDao() {
		return new UserDaoImpl();
	}

	public static RecruitTypeDao getRecruitTypeDao() {
		return new RecruitTypeDaoImpl();
	}
}
