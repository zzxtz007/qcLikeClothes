package com.qcsj.dao.impl;

import com.qcsj.dao.UserDao;
import com.qcsj.dao.util.MySqlJDBC;
import com.qcsj.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao {
	@Override
	public User queryByUsername(String phone) throws SQLException {
		//language=MySQL
		String sql = "SELECT\n" +
				"  user_id,\n" +
				"  user_name,\n" +
				"  password\n" +
				"FROM qcr_user\n" +
				"WHERE (mobile_phone = ? OR user_name = ?) AND delete_flag = 0";
		ArrayList p = new ArrayList();
		p.add(phone);
		p.add(phone);
		Object o = MySqlJDBC.execute(sql, p, MySqlJDBC.SELECT);
		if (o == null) {
			return null;
		}
		ResultSet rs = (ResultSet) o;
		User u = null;
		while (rs.next()) {
			u = new User();
			u.setId(rs.getInt(1));
			u.setName(rs.getString(2));
			u.setPassword(rs.getString(3));
		}
		MySqlJDBC.clossConnection();
		return u;
	}
}
