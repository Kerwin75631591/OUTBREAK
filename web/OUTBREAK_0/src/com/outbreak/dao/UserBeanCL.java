package com.outbreak.dao;

import com.outbreak.entity.UserBean;

import java.sql.SQLException;

import com.outbreak.util.DBConnect;

public class UserBeanCL {
	public DBConnect db;
	/*
	*名称：构造函数
	*描述：初始化UserBeanCL
	*参数：void
	*返回类型：void
	*作者：周于楷
	*/
	public UserBeanCL() {
		db = new DBConnect();
		try {
			db.connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	*名称：用户注册函数
	*描述：注册用户
	*参数：UserBean ub
	*返回类型：boolean
	*作者：周于楷
	*/
	public boolean Register(UserBean ub) {
		int judge = 10;
		try {
			judge = db.searchUser(false, ub.getEmail(), ub.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (judge == 0) {
			try {
				db.insertUser(ub.getEmail(), ub.getPassword(), ub.getPhoneNumber(), ub.getName(), ub.getAddress());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	/*
	*名称：用户登录函数
	*描述：登录用户
	*参数：UserBean ub
	*返回类型：boolean
	*作者：周于楷
	*/
	public boolean Login(UserBean ub) {
		int judge = 10;
		try {
			judge = db.searchUser(true, ub.getEmail(), ub.getPassword());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (judge == 0)
			return true;
		else
			return false;

	}
}
