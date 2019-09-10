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
	/*
	*名称：数据库驱动函数
	*描述：初始化数据库驱动
	*参数：void
	*返回类型：void
	*作者：周于楷
	*/
	public DBConnect() {

		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			System.out.println("无法找到驱动类");
		}
	}

	/*
	*名称：数据库初始化函数
	*描述：初始化数据库格式
	*参数：void
	*返回类型：void
	*作者：周于楷
	*/
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

	/*
	*名称：数据库连接函数
	*描述：连接数据库
	*参数：void
	*返回类型：void
	*作者：周于楷
	*/
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

	/*
	*名称：新建用户函数
	*描述：将新用户插入数据库
	*参数：String email, String password, String phoneNumber, String name, String address
	*返回类型：void
	*作者：周于楷
	*/
	public void insertUser(String email, String password, String phoneNumber, String name, String address)
			throws SQLException {
		String sql = "SELECT id FROM UserTable ";
		rs = statement.executeQuery(sql);
		int id = 0;
		while (rs.next()) {
			id = rs.getInt("id");
		}
		id = id + 1;
		sql = "INSERT INTO UserTable(id ,email ,password ,phoneNumber , name , address,Public)values(?,?,?,?,?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.setString(2, email);
		pstmt.setString(3, password);
		pstmt.setString(4, phoneNumber);
		pstmt.setString(5, name);
		pstmt.setString(6, address);
		pstmt.setString(7, "0000");
		pstmt.addBatch();
		pstmt.clearParameters();
		pstmt.executeBatch();
		pstmt.clearBatch();
	}

	/*
	*名称：用户删除函数
	*描述：删除用户
	*参数：String email
	*返回类型：void
	*作者：周于楷
	*/
	public void deleteUser(String email) throws SQLException {
		String sql = "DELETE * FROM UserTable WHERE email = " + email;
		statement.execute(sql);
	}

	/*
	*名称：用户注册函数
	*描述：自动注册用户
	*参数：String email
	*返回类型：void
	*作者：周于楷
	*/
	public boolean RegistUser(String email, String name, String phoneNum) throws SQLException {
		String sql = "SELECT*FROM UserTable WHERE email = '" + email + "'";
		rs = statement.executeQuery(sql);
		System.out.println("rs表已创建");
		if(rs!=null)
			return true;
		else {
			insertUser(email, "outbreak123", phoneNum, name, null);
			return false;
		}
	}

	/*
	*名称：会议搜索函数
	*描述：搜索所有待审核的会议
	*参数: void
	*返回类型：ResultSet
	*作者：周于楷
	*/
	public ResultSet searchMeeting() throws SQLException {
		String sql = "SELECT * FROM MeetingTable WHERE state = 1";
		rs = statement.executeQuery(sql);
		return rs;
	}
	/*
	*名称：会议搜索函数
	*描述：搜索id的会议
	*参数：int id
	*返回类型：ResultSet
	*作者：周于楷
	*/
	public ResultSet searchMeeting(int id) throws SQLException {
		String sql = "SELECT * FROM MeetingTable WHERE id = '" + id + "'";
		rs = statement.executeQuery(sql);
		return rs;
	}

	/*
	*名称：会议状态更新函数
	*描述：更新会议状态
	*参数：int id, int state
	*返回类型：void
	*作者：周于楷
	*/
	public void updateMeeting(int id, int state) throws SQLException {
		String sql = "UPDATE MeetingTable SET state = " + state + " WHERE   id = '" + id + "'";
		System.out.println(sql);
		statement.executeUpdate(sql);

	}
	/*
	*名称：参会人员搜索函数	
	*描述：搜索某id会议的参会人员
	*参数：int mid
	*返回类型：ResultSet
	*作者：周于楷
	*/
	public ResultSet searchPeople(int mid) throws SQLException {
		String sql = "SELECT * FROM PeopleTable WHERE mid = '" + mid + "'";
		rs = statement.executeQuery(sql);
		return rs;
	}

	/*
	*名称：全局消息发送函数	
	*描述：发送全局消息
	*参数：String message
	*返回类型：void
	*作者：周于楷
	*/
	@SuppressWarnings("deprecation")
	public void insertMessage(String message) throws SQLException {
		String sql = "INSERT INTO MessageTable(message ,time)values(?,?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, message);
		pstmt.setString(2, new Date().toLocaleString());
		pstmt.addBatch();
		pstmt.clearParameters();
		pstmt.executeBatch();
		pstmt.clearBatch();
	}

	/*
	*名称：数据库连接关闭函数
	*描述：关闭数据库连接
	*参数：void
	*返回类型：void
	*作者：周于楷
	*/
	public void close() {
		try {
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws SQLException {
		DBConnect db = new DBConnect();
		db.initiazation();
	}

}
