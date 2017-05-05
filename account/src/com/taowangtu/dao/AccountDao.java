package com.taowangtu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.taowangtu.utils.JDBCUtils;

public class AccountDao {

	public void accountOut(String fromUser, String money) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			String sql = "update account set money=money-? where username=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, money);
			pstmt.setString(2, fromUser);

			int i = pstmt.executeUpdate();
			if (i > 0) {
				System.out.println("转出" + money + "元");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 注意这里不能释放conn
			JDBCUtils.closeResource(rs, pstmt);
		}
	}

	public void accountIn(String toUser, String money) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			String sql = "update account set money=money+? where username=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, money);
			pstmt.setString(2, toUser);

			int i = pstmt.executeUpdate();
			if (i > 0) {
				System.out.println("转入" + money + "元");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResource(rs, pstmt);
		}
	}

}
