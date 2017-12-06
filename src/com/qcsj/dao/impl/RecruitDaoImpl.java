package com.qcsj.dao.impl;

import com.qcsj.dao.RecruitDao;
import com.qcsj.dao.util.MySqlJDBC;
import com.qcsj.entity.Recruit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecruitDaoImpl implements RecruitDao {
	/**
	 * 拼接查询语句
	 * <p>
	 * 需要查询的条件传入true，不需要查询的传入false
	 * <p>
	 * 全部为false则查询全部记录
	 *
	 * @param recruitId 职位分类ID
	 * @param company   公司名称
	 * @param job       职位名称
	 * @param typeId    类型id
	 * @return 拼接后的查询语句
	 */
	private String combineQuerySql(boolean recruitId, boolean company, boolean job, boolean typeId
	) {
		return "SELECT\n" +
				"  recruit_id,\n" +
				"  company,\n" +
				"  JOB,\n" +
				"  q.type_id type_id,\n" +
				"  q.type_name type_name,\n" +
				"  recruit_count,\n" +
				"  hits,\n" +
				"  hot_flag,\n" +
				"  verify_flag\n" +
				"FROM qcr_recruit\n" +
				"  JOIN qcr_recruit_type q\n" +
				"    ON qcr_recruit.type_id = q.type_id\n" +
				"WHERE q.delete_flag = 0 AND qcr_recruit.delete_flag = 0" +
				(recruitId ? " AND `recruit_id` = ?" : "") +
				(typeId ? " AND `type_id` = ?" : "") +
				(company ? " AND `company` like ?" : "") +
				(job ? " AND `job` LIKE ?" : "") +
				" ORDER BY `recruit_id` , `update_date` DESC LIMIT ?, ?";
	}

	@Override
	public List<Recruit> getRecruitByPage(Integer pageSize, Integer pageNum) throws SQLException {
		String sql = combineQuerySql(false, false, false, false);
		ArrayList<Object> p = new ArrayList<>();
		p.add((pageNum - 1) * pageSize);
		p.add(pageNum * pageSize);
		Object o = MySqlJDBC.execute(sql, p, MySqlJDBC.SELECT);
		if (o == null) {
			return null;
		}
		ResultSet rs = (ResultSet) o;
		List<Recruit> a = generateModelArr(rs);
		MySqlJDBC.clossConnection();
		return a;
	}

	@Override
	public Integer getRecruitPageNum(Integer pageSize) throws SQLException {
		//language=MySQL
		String sql = "SELECT\n" +
				"  count(recruit_id)\n" +
				"FROM qcr_recruit\n" +
				"  JOIN qcr_recruit_type ON qcr_recruit.type_id = qcr_recruit_type.type_id\n" +
				"WHERE qcr_recruit.delete_flag = 0 AND qcr_recruit_type.delete_flag = 0";
		Object o = MySqlJDBC.execute(sql, 2);
		if (o == null) {
			return null;
		}
		ResultSet rs = (ResultSet) o;
		Integer a = null;
		if (rs.next()) {
			a = rs.getInt(1);
		}
		MySqlJDBC.clossConnection();
		if (a == null) {
			return null;
		}
		System.out.println("a----" + a);
		return a % pageSize == 0 ? a / pageSize : a / pageSize + 1;
	}

	@Override
	public Integer insertRecruit(String company, String job, Integer typeId, Integer recruitCount, String salary, String workPlace, String positionStatement, String jobRequirements, Integer uid) throws SQLException {
		//language=MySQL
		String sql = "INSERT INTO qcr_recruit (company, JOB, type_id, recruit_count, salary, work_place, position_statement, job_requirements, create_user, update_user, create_date, update_date)\n" +
				"VALUES\n" +
				"  (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,\n" +
				" now(), now());";
		ArrayList<Object> p = new ArrayList<>();
		p.add(company);
		p.add(job);
		p.add(typeId);
		p.add(recruitCount);
		p.add(salary);
		p.add(workPlace);
		p.add(positionStatement);
		p.add(jobRequirements);
		p.add(uid);
		p.add(uid);
		Object o = MySqlJDBC.execute(sql, p, 1);
		if (o == null) {
			return null;
		}
		return o.hashCode();
	}

	@Override
	public Integer updateRecruit(String company, String job, Integer typeId, Integer recruitCount, String salary, String workPlace, String positionStatement, String jobRequirements, Integer uid,Integer recruitId) throws SQLException {
		//language=MySQL
		String sql = "UPDATE qcr_recruit\n" +
				"SET company   = ?, JOB = ?, type_id = ?, recruit_count = ?, salary = ?,\n" +
				"  work_place  = ?, position_statement = ?, job_requirements = ?,\n" +
				"  update_user = ?, update_date = now()\n" +
				"WHERE recruit_id = ?";
		ArrayList<Object> p = new ArrayList<>();
		p.add(company);
		p.add(job);
		p.add(typeId);
		p.add(recruitCount);
		p.add(salary);
		p.add(workPlace);
		p.add(positionStatement);
		p.add(jobRequirements);
		p.add(uid);
		p.add(recruitId);
		Object o = MySqlJDBC.execute(sql, p, 1);
		MySqlJDBC.clossConnection();
		if (o == null) {
			return null;
		}
		return o.hashCode();
	}


	@Override
	public Integer deleteRecruit(Integer recruitId, Integer userId) throws SQLException {
		String sql = "UPDATE qcr_recruit\n" +
				"SET delete_flag =1,update_user = ? ,update_date = now() \n" +
				"WHERE type_id = ?";
		ArrayList<Object> p = new ArrayList<>(1);
		p.add(userId);
		p.add(recruitId);
		Object o = MySqlJDBC.execute(sql, p, 1);
		MySqlJDBC.clossConnection();
		if (o == null) {
			return null;
		}
		return o.hashCode();
	}


	private List<Recruit> generateModelArr(ResultSet rs) throws SQLException {
		List<Recruit> list = new ArrayList<>();
		while (rs.next()) {
			Recruit rt = new Recruit();
			rt.setId(rs.getInt(1));
			rt.setCompany(rs.getString(2));
			rt.setJob(rs.getString(3));
			rt.setTypeId(rs.getInt(4));
			rt.setTypeName(rs.getString(5));
			rt.setRecruitCount(rs.getInt(6));
			rt.setSalary(rs.getString(7));
			list.add(rt);
		}
		int len = list.size();
		return len == 0 ? null : list;
	}
}
