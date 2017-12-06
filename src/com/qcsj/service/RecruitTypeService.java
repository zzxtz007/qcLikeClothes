package com.qcsj.service;


import com.qcsj.service.ServiceUtil.SuperInfo;

import javax.servlet.http.HttpSession;

public interface RecruitTypeService {
	SuperInfo showManageType(String pageSize, String pageNum, HttpSession session);

	SuperInfo getTotalRecruitTypePage(Integer pageSize, HttpSession session);

	SuperInfo getAllFathersType(HttpSession session);

	SuperInfo updateTypeById(String id, String name, String description, String supId, HttpSession session);

	SuperInfo insertType(String id, String name, String description, String supId, HttpSession session);

	SuperInfo deleteType(String id, HttpSession session);
}
