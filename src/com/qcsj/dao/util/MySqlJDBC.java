package com.qcsj.dao.util;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

/**
 * 1 为增删改
 * 2 为查
 */
public class MySqlJDBC {

	static Connection conn = null;
	static DataSource ds = null;
	public static final int UPDATE = 1;
	public static final int SELECT = 2;

	//静态代码块
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mysql_qcsj");

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}


	/**
	 * @param sql  需要操作的语句
	 * @param type 需要操作的类型(1 为增删改 2 为查)
	 * @return object 可能为空注意添加判断 还可能返回int 和 ResultSet
	 */
	public static Object execute(String sql, int type) {
		try {
			conn = ds.getConnection();
			switch (type) {
				case UPDATE:
					return exeOper(sql);
				case SELECT:
					return exeSelect(sql);
				default:
					return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 若是type = 2/Select 需要手动调用clossConnection方法
	 * @param sql  需要操作的语句
	 * @param type 需要操作的类型(1 为增删改 2 为查)
	 * @return object 可能为空注意添加判断 还可能返回int 和 ResultSet
	 */
	public static Object execute(String sql, ArrayList<Object> aryPary, int type) {
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		switch (type) {
			case UPDATE:
				break;
			case SELECT:
				break;
			default:
				return null;
		}
		ResultSet rs = null;
		int ret = 0;
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			int count = aryPary.size();
			int i = 1;
			while (i <= count) {
				Object o = aryPary.get(i - 1);
				if (o == null) {

					pst.setNull(i, Types.DOUBLE);
				} else if (o instanceof String) {
					pst.setString(i, (String) o);
				} else if (o instanceof Integer) {
					pst.setInt(i, (Integer) o);
				} else if (o instanceof Byte) {
					pst.setByte(i, (Byte) o);
				} else if (o instanceof Float) {
					pst.setFloat(i, (Float) o);
				} else if (o instanceof Boolean) {
					pst.setBoolean(i, (Boolean) o);
				} else if (o instanceof Double) {
					pst.setDouble(i, (Double) o);
				} else if (o instanceof Long) {
					pst.setLong(i, (Long) o);
				} else if (o instanceof Date) {
					pst.setDate(i, (Date) o);
				}
				i++;
			}
			switch (type) {
				case UPDATE:
					ret = pst.executeUpdate();
					closeResoure(null, pst);
					clossConnection();
					break;
				case SELECT:
					rs = pst.executeQuery();
					break;
				default:
					break;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		switch (type) {
			case UPDATE:
				return ret;
			case SELECT:
				return rs;
			default:
				return null;
		}
	}

	/**
	 * 注意在此不关闭 rs以及pst资源
	 * 如果关闭将不能在调用处使用rs
	 * 封装执行不带？select语句
	 *
	 * @param sql
	 * @return
	 */
	private static ResultSet exeSelect(String sql) {
		ResultSet rs = null;
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 执行方法 需要？ update insert delete
	 *
	 * @param sql
	 * @return
	 */
	private static int exeOper(String sql) {
		int ret = 0;
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ret = pst.executeUpdate();
			closeResoure(null, pst);
			clossConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}


	/**
	 * 关闭资源的方法，关闭非静态资源
	 *
	 * @param rs
	 * @param st
	 */
	private static void closeResoure(ResultSet rs, Statement st) {
		try {

			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void clossConnection() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}



