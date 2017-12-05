package com.qcsj.service;


import com.qcsj.service.ServiceUtil.SuperInfo;

import javax.servlet.http.HttpSession;

public interface RecruitTypeService {
	SuperInfo ShowManageType(String pageSize,String pageNum,HttpSession session);
	SuperInfo getTotalRecruitPage(Integer pageSize,HttpSession session);
}
