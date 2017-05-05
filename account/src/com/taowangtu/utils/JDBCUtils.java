package com.taowangtu.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JDBCUtils {
	static final String DRIVERCLASS;
	static final String URL;
	static final String USER;
	static final String PASSWORD;

	private static ThreadLocal<Connection> thread = new ThreadLocal<>();

	static {
		ResourceBundle bundle = ResourceBundle.getBundle("db");
		DRIVERCLASS = bundle.getString("driverClass");
		URL = bundle.getString("url");
		USER = bundle.getString("user");
		PASSWORD = bundle.getString("password");
	}
	static {
		try {
			Class.forName(DRIVERCLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("注册失败，请检查驱动jar包");
		}
	}

	/**
	 * @return
	 * @throws SQLException
	 *             从当前线程获取连接
	 */
	public static Connection getConnection() throws SQLException {
		Connection conn = thread.get();
		if (conn == null) {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			// 与当前线程绑定
			thread.set(conn);
		}
		return conn;
	}

	/**
	 * @param conn
	 * @param pstmt
	 * @param rs
	 *            释放资源
	 */
	public static void closeResource(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		closeResultSet(rs);
		closeResource(conn, pstmt);
	}

	public static void closeResource(Connection conn, PreparedStatement pstmt) {
		closePreparedStatement(pstmt);
		closeConn(conn);
	}

	public static void closeResource(ResultSet rs, PreparedStatement pstmt) {
		closeResultSet(rs);
		closePreparedStatement(pstmt);
	}

	public static void closePreparedStatement(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			pstmt = null;
		}
	}

	public static void closeConn(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
				// 与当前线程解绑
				thread.remove();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
	}

}
