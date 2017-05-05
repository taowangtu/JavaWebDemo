package com.taowangtu.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.taowangtu.dao.AccountDao;
import com.taowangtu.utils.JDBCUtils;

public class AccountService {

	public void account(String fromUser, String toUser, String money) {
		Connection conn = null;
		try {
			// 获取连接,从线程中获取保证是同一个连接
			conn = JDBCUtils.getConnection();
			// 开启事务
			conn.setAutoCommit(false);
			// 转出
			AccountDao accountDao = new AccountDao();
			accountDao.accountOut(fromUser, money);
			// int a=1/0; //模拟转账异常，验证事务控制
			// 转入
			accountDao.accountIn(toUser, money);
			// 提交事务
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			JDBCUtils.closeConn(conn);
		}
	}

}
