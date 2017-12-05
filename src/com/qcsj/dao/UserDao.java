package com.qcsj.dao;

import com.qcsj.entity.User;

import java.sql.SQLException;

public interface UserDao {
	User queryByUsername(String phone) throws SQLException;
}
