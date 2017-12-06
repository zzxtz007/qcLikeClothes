package com.qcsj.service;

import com.qcsj.service.ServiceUtil.SuperInfo;

import javax.servlet.http.HttpSession;

/**
 * @author Ice_Dog
 */
public interface RecruitService {
	SuperInfo insertRecruit(String company, String job,String typeId, String recruitCount, String salary, String workPlace, String positionStatement, String jobRequirements, HttpSession session);

	SuperInfo deleteRecruit(String id, HttpSession session);

	SuperInfo getTotalRecruitPage(int i, HttpSession session);

	SuperInfo queryRecruit(String pageSize, String pageNum, HttpSession session);

	SuperInfo updateRecruitById(String company, String job, String typeId, String recruitCount, String salary, String workPlace, String positionStatement, String jobRequirements, String hits, String hotFlag, String verifyFlag, HttpSession session, String recruitId);
}
