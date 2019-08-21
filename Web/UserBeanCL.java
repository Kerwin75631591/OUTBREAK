package web;
import web.UserBean;

import java.sql.SQLException;

import web.DBConnect;
public class UserBeanCL {
	public DBConnect db;
	public UserBeanCL() {
		db=new DBConnect();
	}
	//注册用户
	public int Register(UserBean ub) {
		int judge = 10;
		try {
			judge = db.searchUser(false, ub.getEmail(), ub.getPassword());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(judge==0) {
			try {
				db.insertUser(ub.getEmail(), ub.getPassword(), ub.getPhoneNumber(), ub.getName(), ub.getAddress());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return judge;
	}
	//登录用户
		public int Login(UserBean ub) {
			int judge = 10;
			try {
				judge = db.searchUser(true, ub.getEmail(), ub.getPassword());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return judge;
		}
}
