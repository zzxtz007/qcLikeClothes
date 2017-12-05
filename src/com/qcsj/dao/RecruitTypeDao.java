package com.qcsj.dao;

import com.qcsj.entity.RecruitType;

import java.sql.SQLException;
import java.util.List;

public interface RecruitTypeDao {
	List<RecruitType> getRecruitTypeByPage(Integer pageSize, Integer pageNum) throws SQLException;

	Integer getRecruitTypePageNum(Integer pageSize) throws SQLException;

	Integer insertRecruitType(String name, String desciption, Integer supId, Integer uid, Integer typeId) throws SQLException;

	List<RecruitType> getRecruitTypeFathers() throws SQLException;
}
