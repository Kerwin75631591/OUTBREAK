package management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

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

	// 初始化数据库
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

			// 创建用户表并记录 id，邮箱，密码，联系方式，名字，地址，
			statement.executeUpdate(
					"create table UserTable(id integer(5),email varchar(20),password varchar(20),phoneNumber varchar(11), name varchar(20), address varchar(20))");

			// 创建会议表并记录 id，邮箱，密码，联系方式，名字，地址，
			statement.executeUpdate(
					"create table MeetingTable(id integer(5),time date,place varchar(20),name varchar(20), "
							+ "content varchar(20), host varchar(20), state integer(5), PeopleNum integer(5),ArrivalNum integer(5))");

			// 创建人员表并记录 会议id，人员id，是否参加
			statement.executeUpdate("create table PeopleTable(Mid integer(5),Uid integer(5),TOF tinyint)");

		} catch (ClassNotFoundException e) {
			System.out.println("无法找到驱动类");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 连接数据库
	public void connect() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String dbURL = "jdbc:mysql://localhost:3306/"
					+ "UserDB?user=root&password=749847569&serverTimezone=GMT%2B8&useSSL=false";
			connection = DriverManager.getConnection(dbURL);
			statement = connection.createStatement();
			System.out.println("数据库已连接");
		} catch (ClassNotFoundException e) {
			System.out.println("无法找到驱动类");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("数据库无法连接");
		}
	}

	
	//MeetingTable搜索所有待审核的会议，返回resultset
	public ResultSet searchMeeting() throws SQLException {
		String sql = "SELECT * FROM MeetingTable WHERE state = 1";
		rs = statement.executeQuery(sql);
		return rs;
	}
	

	// MeetingTable修改某个会议的状态
	public void updateMeeting(int id, int state) throws SQLException {
		String sql = "UPDATE MeetingTable SET state = "+state+" WHERE   id = '" + id + "'";
		System.out.println(sql);
		rs = statement.executeQuery(sql);

	}

	// 关闭数据库连接
	public void close() {
		try {
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 数据库初始化
	public static void main(String[] args) throws SQLException {
		DBConnect db = new DBConnect();
		db.initiazation();
	}

}
