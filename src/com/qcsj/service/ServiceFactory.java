package com.qcsj.service;


import com.qcsj.dao.RecruitDao;
import com.qcsj.dao.impl.RecruitDaoImpl;
import com.qcsj.service.impl.RecruitServiceImpl;
import com.qcsj.service.impl.RecruitTypeServiceImpl;
import com.qcsj.service.impl.UserServiceImpl;

public class ServiceFactory {
	public static UserService getUserService(){
		return new UserServiceImpl();
	}
	public static RecruitTypeService getRecruitTypeService(){
		return new RecruitTypeServiceImpl();
	}

	public static RecruitService getRecruitService() {
		return new RecruitServiceImpl();
	}
}
