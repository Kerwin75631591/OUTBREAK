package com.login.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnect {
	public Connection connection = null;
	public Statement statement = null;
	public ResultSet rs = null;

	public DBConnect() {

		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			System.out.println("无法找到驱动类");
		}
	}
	//初始化数据库
	public void initiazation() throws SQLException {
		try {
					// 连接已有数据库
					String dbURL = "jdbc:mysql://localhost:3306/"
							+ "MyDB?user=root&password=749847569&serverTimezone=GMT%2B8&useSSL=false";
					connection = DriverManager.getConnection(dbURL);
					statement = connection.createStatement();
					// 新建数据库
					statement.executeUpdate("create database UserDB");
					// 打开新建的数据库
					statement.close();
					connection.close();
					Class.forName("com.mysql.jdbc.Driver");
					dbURL = "jdbc:mysql://localhost:3306/"
							+ "UserDB?user=root&password=749847569&serverTimezone=GMT%2B8&useSSL=false";
					connection = DriverManager.getConnection(dbURL);
					statement = connection.createStatement();
					// 创建新表并记录    id，邮箱，密码，联系方式，名字，地址，
					statement.executeUpdate(
							"create table UserTable(id integer(5),email varchar(20),password varchar(20),phoneNumber varchar(11), name varchar(20), address varchar(20))");
		}catch (ClassNotFoundException e) {
			System.out.println("无法找到驱动类");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//连接数据库
	public void connect() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String dbURL = "jdbc:mysql://localhost:3306/"
					+ "UserDB?user=root&password=749847569&serverTimezone=GMT%2B8&useSSL=false";
			connection = DriverManager.getConnection(dbURL);
			statement = connection.createStatement();
		} catch (ClassNotFoundException e) {
			System.out.println("无法找到驱动类");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 在数据库中加入新的数据
	public void insertUser( String email, String password, String phoneNumber,String name,String address) throws SQLException {
		String sql = "SELECT*FROM UserTable ";
		rs = statement.executeQuery(sql);
		int id=0;
		while (rs.next()) {
			id=rs.getInt("id");
		}
		id=id+1;
		sql = "INSERT INTO UserTable(id ,email ,password ,phoneNumber , name , address)values(?,?,?,?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.setString(2, email);
		pstmt.setString(3, password);
		pstmt.setString(4, phoneNumber);
		pstmt.setString(5, name);
		pstmt.setString(6, address);
		pstmt.addBatch();
		pstmt.clearParameters();
		pstmt.executeBatch();
		pstmt.clearBatch();
	}

	// 删除数据
	public void deleteUser(String email) throws SQLException {
		String sql = "DELETE FROM UserTable WHERE email = "+email;
		statement.execute(sql);
	}

	// 登录(true)&注册(false)检测 返回值0为通过，登录中1为密码错误，2为账号不存在，注册中1为该邮箱已注册
	public int searchUser(boolean judge,String email,String password ) throws SQLException {
		String sql = "SELECT*FROM UserTable";
		rs = statement.executeQuery(sql);
		
		if(judge) {
			while (rs.next()) {
				if(email.equals(rs.getString("email"))){
					if(password.equals(rs.getString("password")))
						return 0;
					else
						return 1;
				}
			}
			return 2;
		}
		else {
			while (rs.next()) {
				if(email.equals(rs.getString("email")))
						return 1;
			}
			return 0;
		}
	}
	
	//关闭数据库连接
	public void close() {
		try {
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	//数据库初始化
	public static void main(String[] args) throws SQLException {
		DBConnect db=new DBConnect();
		db.initiazation();
	}
	*/

}
