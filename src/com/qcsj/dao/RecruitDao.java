package com.qcsj.dao;

import com.qcsj.entity.Recruit;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Ice_Dog
 */
public interface RecruitDao {
	/**
	 * 添加信息
	 *
	 * @param company           公司名
	 * @param job               职位
	 * @param typeId            类型id
	 * @param recruitCount      需要人数
	 * @param salary            薪资范围
	 * @param workPlace         工作地址
	 * @param positionStatement 岗位职责
	 * @param jobRequirements   任职要求
	 * @param uid               操作用户id
	 * @return
	 * @throws SQLException
	 */
	Integer insertRecruit(String company, String job, Integer typeId, Integer recruitCount, String salary, String workPlace, String positionStatement, String jobRequirements, Integer uid) throws SQLException;

	/**
	 * 分页查询
	 *
	 * @param pageSize 每页的大小
	 * @param pageNum  第几页
	 * @return 返回结果集
	 * @throws SQLException
	 */
	List<Recruit> getRecruitByPage(Integer pageSize, Integer pageNum) throws SQLException;

	Integer getRecruitPageNum(Integer pageSize) throws SQLException;

	Integer updateRecruit(String company, String job, Integer typeId, Integer recruitCount, String salary, String workPlace, String positionStatement, String jobRequirements, Integer hitss, Integer hotflag, Integer verifyflag, Integer uid, Integer recruitId) throws SQLException;

	Integer deleteRecruit(Integer recruitId, Integer userId) throws SQLException;

}
