package com.qcsj.dao;

import com.qcsj.entity.RecruitType;

import java.sql.SQLException;
import java.util.List;

public interface RecruitTypeDao {
	List<RecruitType> getRecruitTypeByPage(Integer pageSize, Integer pageNum) throws SQLException;

	Integer getRecruitTypePageNum(Integer pageSize) throws SQLException;

	Integer insertRecruitType(String name, String desciption, Integer supId, Integer uid) throws SQLException;

	List<RecruitType> getRecruitTypeFathers() throws SQLException;

	Integer updateRecruitType(String name, String desciption, Integer supId, Integer uid, Integer typeId) throws SQLException;

	Integer deleteRecruitType(Integer typeId, Integer userId) throws SQLException;


	List<RecruitType> getAllSonByFatherId(Integer supId) throws SQLException;
}
