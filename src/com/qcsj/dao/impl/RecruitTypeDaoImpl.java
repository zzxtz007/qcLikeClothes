package com.qcsj.dao.impl;

import com.qcsj.dao.RecruitTypeDao;
import com.qcsj.dao.util.MySqlJDBC;
import com.qcsj.entity.RecruitType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecruitTypeDaoImpl implements RecruitTypeDao {

	/**
	 * 拼接查询语句
	 * <p>
	 * 需要查询的条件传入true，不需要查询的传入false
	 * <p>
	 * 全部为false则查询全部记录
	 *
	 * @param recruitTypeId          职位分类ID
	 * @param recruitTypeName        职位名称
	 * @param recruitTypeDescription 职位描述
	 * @param supId                  上级职位ID
	 * @param deleteFlag             已删除
	 * @return 拼接后的查询语句
	 */
	private String combineQuerySql(boolean recruitTypeId, boolean recruitTypeName, boolean recruitTypeDescription, boolean supId,
	                               boolean deleteFlag) {
		return "SELECT " +
				"  t1.`type_id`,\n" +
				"  t1.`type_name`,\n" +
				"  t1.`type_description`,\n" +
				"  t1.`sub_id`,\n" +
				"  t2.`type_name`\n" +
				" FROM `qcr_recruit_type` t1 " +
				"JOIN  `qcr_recruit_type` t2 ON t2.`type_id`=t1.`sub_id`" +
				" WHERE 1 = 1" +
				(recruitTypeId ? " AND t1.`type_id` = ?" : "") +
				(recruitTypeName ? " AND t1.`type_name` like ?" : "") +
				(recruitTypeDescription ? " AND t1.`type_description` LIKE ?" : "") +
				(supId ? " AND t1.`sup_id` LIKE ?" : "") +
				(deleteFlag ? " AND t1.`delete_flag` = ?" : "") +
				" ORDER BY t1.`type_id` DESC, t1.`update_date` DESC LIMIT ?, ?";
	}

	@Override
	public ArrayList<RecruitType> getRecruitTypeByPage(Integer pageSize, Integer pageNum) throws SQLException {
		String sql = combineQuerySql(false, false, false, false, false);
		ArrayList p = new ArrayList();
		p.add((pageNum - 1) * pageSize);
		p.add(pageNum * pageSize);
		Object o = MySqlJDBC.execute(sql, p, MySqlJDBC.SELECT);
		if (o == null) {
			return null;
		}
		ResultSet rs = (ResultSet) o;
		ArrayList<RecruitType> a = new ArrayList<>();
		while (rs.next()) {
			RecruitType rt = new RecruitType();
			rt.setId(rs.getInt(1));
			rt.setName(rs.getString(2));
			rt.setDescription(rs.getString(3));
			rt.setSupId(rs.getInt(4));
			rt.setSupName(rs.getString(5));
			a.add(rt);
		}
		MySqlJDBC.clossConnection();
		return a;
	}

	@Override
	public Integer getRecruitTypePageNum(Integer pageSize) throws SQLException{
		//language=MySQL
		String sql = "SELECT count(`type_id`)\n" +
				"FROM `qcr_recruit_type`";
		Object o = MySqlJDBC.execute(sql,2);
		if (o==null){
			return null;
		}
		ResultSet rs = (ResultSet) o;
		Integer a =null;
		if (rs.next()){
			a= rs.getInt(1);
		}
		MySqlJDBC.clossConnection();
		return a%pageSize==0?a/pageSize:a/pageSize+1;
	}
}
