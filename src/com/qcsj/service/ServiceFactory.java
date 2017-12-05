package com.qcsj.service;


import com.qcsj.service.impl.RecruitTypeServiceImpl;
import com.qcsj.service.impl.UserServiceImpl;

public class ServiceFactory {
	public static UserService getUserService(){
		return new UserServiceImpl();
	}
	public static RecruitTypeService getRecruitTypeService(){
		return new RecruitTypeServiceImpl();
	}

}
