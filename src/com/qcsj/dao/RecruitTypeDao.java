package com.qcsj.dao;

import com.qcsj.entity.RecruitType;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RecruitTypeDao {
	ArrayList<RecruitType> getRecruitTypeByPage(Integer pageSize, Integer pageNum) throws SQLException;

	Integer getRecruitTypePageNum(Integer pageSize) throws SQLException;
}
