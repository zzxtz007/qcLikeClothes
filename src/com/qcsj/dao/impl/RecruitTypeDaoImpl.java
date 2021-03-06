package com.qcsj.dao.impl;

import com.qcsj.dao.RecruitTypeDao;
import com.qcsj.dao.util.MySqlJDBC;
import com.qcsj.entity.RecruitType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ice_dog
 */
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
	 * @return 拼接后的查询语句
	 */
	private String combineQuerySql(boolean recruitTypeId, boolean recruitTypeName, boolean recruitTypeDescription, boolean supId
	) {
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
				" AND t1.`delete_flag` = 0 " +
				" ORDER BY t1.`type_id` DESC, t1.`update_date` DESC LIMIT ?, ?";
	}

	@Override
	public List<RecruitType> getRecruitTypeByPage(Integer pageSize, Integer pageNum) throws SQLException {
		String sql = combineQuerySql(false, false, false, false);
		ArrayList<Object> p = new ArrayList<>();
		p.add((pageNum - 1) * pageSize);
		p.add(pageNum * pageSize);
		Object o = MySqlJDBC.execute(sql, p, MySqlJDBC.SELECT);
		if (o == null) {
			return null;
		}
		ResultSet rs = (ResultSet) o;
		List<RecruitType> a = generateModelArr(rs);
		MySqlJDBC.clossConnection();
		return a;
	}

	@Override
	public Integer getRecruitTypePageNum(Integer pageSize) throws SQLException {
		//language=MySQL
		String sql = "SELECT count(`type_id`)\n" +
				"FROM `qcr_recruit_type` WHERE delete_flag = 0";
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
	public Integer insertRecruitType(String name, String desciption, Integer supId, Integer uid) throws SQLException {
		//language=MySQL
		String sql = "INSERT INTO qcr_recruit_type (type_name, type_description, sub_id, create_user, update_user, create_date, update_date, delete_flag)\n" +
				"VALUES (?,?,?,?,?,now(),now(),0)\n";
		ArrayList<Object> p = new ArrayList<>();
		p.add(name);
		p.add(desciption);
		p.add(supId);
		p.add(uid);
		p.add(uid);
		Object o = MySqlJDBC.execute(sql, p, 1);
		if (o == null) {
			return null;
		}
		return o.hashCode();
	}

	@Override
	public Integer updateRecruitType(String name, String desciption, Integer supId, Integer uid, Integer typeId) throws SQLException {
		//language=MySQL
		String sql = "UPDATE qcr_recruit_type\n" +
				"SET type_name = ?,type_description = ?,sub_id = ?,update_user = ? ,update_date = now() WHERE type_id = ?";
		ArrayList<Object> p = new ArrayList<>();
		p.add(name);
		p.add(desciption);
		p.add(supId);
		p.add(uid);
		p.add(typeId);
		Object o = MySqlJDBC.execute(sql, p, 1);
		MySqlJDBC.clossConnection();
		if (o == null) {
			return null;
		}
		return o.hashCode();
	}


	@Override
	public Integer deleteRecruitType(Integer typeId, Integer userId) throws SQLException {
		String sql = "UPDATE qcr_recruit_type\n" +
				"SET delete_flag =1,update_user = ? ,update_date = now() \n" +
				"WHERE type_id = ?";
		ArrayList<Object> p = new ArrayList<>(1);
		p.add(userId);
		p.add(typeId);
		Object o = MySqlJDBC.execute(sql, p, 1);
		MySqlJDBC.clossConnection();
		if (o == null) {
			return null;
		}
		return o.hashCode();
	}

	@Override
	public List<RecruitType> getAllSonByFatherId(Integer supId) throws SQLException {
		//language=MySQL
		String sql = "SELECT\n" +
				"  q.type_id,\n" +
				"  q.type_name,\n" +
				"  q.type_description,\n" +
				"  q.sub_id,\n" +
				"  q.type_name supname\n" +
				"FROM qcr_recruit_type q JOIN qcr_recruit_type  ON q.sub_id = qcr_recruit_type.type_id\n" +
				"WHERE q.type_id !=qcr_recruit_type.sub_id  AND q.delete_flag =0  AND q.sub_id = ?";
		ArrayList<Object> p = new ArrayList<>();
		p.add(supId);
		Object o = MySqlJDBC.execute(sql, p, MySqlJDBC.SELECT);
		if (o == null) {
			return null;
		}
		ResultSet rs = (ResultSet) o;
		List<RecruitType> a = generateModelArr(rs);
		MySqlJDBC.clossConnection();
		return a;
	}

	@Override
	public List<RecruitType> getRecruitTypeFathers() throws SQLException {
		//language=MySQL
		String sql = "SELECT\n" +
				"  type_id,\n" +
				"  type_name,\n" +
				"  type_description,\n" +
				"  sub_id,\n" +
				"  type_name\n" +
				"FROM qcr_recruit_type\n" +
				"WHERE type_id = qcr_recruit_type.sub_id AND delete_flag=0";
		Object o = MySqlJDBC.execute(sql, MySqlJDBC.SELECT);
		if (o == null) {
			return null;
		}
		ResultSet rs = (ResultSet) o;
		List<RecruitType> a = generateModelArr(rs);

		MySqlJDBC.clossConnection();
		return a;
	}

	private List<RecruitType> generateModelArr(ResultSet rs) throws SQLException {
		List<RecruitType> list = new ArrayList<>();
		while (rs.next()) {
			RecruitType rt = new RecruitType();
			rt.setId(rs.getInt(1));
			rt.setName(rs.getString(2));
			rt.setDescription(rs.getString(3));
			rt.setSupId(rs.getInt(4));
			rt.setSupName(rs.getString(5));
			list.add(rt);
		}
		int len = list.size();
		return len == 0 ? null : list;
	}
}
