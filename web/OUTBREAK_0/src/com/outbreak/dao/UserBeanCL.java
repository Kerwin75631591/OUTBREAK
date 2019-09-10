package com.outbreak.dao;

import com.outbreak.entity.UserBean;

import java.sql.SQLException;

import com.outbreak.util.DBConnect;

public class UserBeanCL {
	public DBConnect db;
	/*
	*���ƣ����캯��
	*��������ʼ��UserBeanCL
	*������void
	*�������ͣ�void
	*���ߣ����ڿ�
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
	*���ƣ��û�ע�ắ��
	*������ע���û�
	*������UserBean ub
	*�������ͣ�boolean
	*���ߣ����ڿ�
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
	*���ƣ��û���¼����
	*��������¼�û�
	*������UserBean ub
	*�������ͣ�boolean
	*���ߣ����ڿ�
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
