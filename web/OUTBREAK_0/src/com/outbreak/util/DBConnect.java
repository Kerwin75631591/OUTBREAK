package com.outbreak.util;
/*@author：周于楷*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.outbreak.entity.InvitedPeople;

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
					+ "UserDB?user=root&password=749847569&serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true";
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
	*名称：用户密码修改函数
	*描述：修改用户密码
	*参数：String email, String newPassword)
	*返回类型：void
	*作者：周于楷
	*/
	public void updateUserpassword(String email, String newPassword) throws SQLException {
		String sql = "UPDATE UserTable SET password = " + newPassword + " WHERE   email = '" + email + "'";
		System.out.println(sql);
		statement.executeUpdate(sql);
	}

	/*
	*名称：用户登录注册函数
	*描述：登录注册判断，UserTable登录(true)&注册(false)检测 返回值0为通过，登录中1为密码错误，2为账号不存在，注册中1为该邮箱已注册
	*参数：boolean judge, String email, String password
	*返回类型：int
	*作者：周于楷
	*/
	public int searchUser(boolean judge, String email, String password) throws SQLException {
		String sql = "SELECT*FROM UserTable";
		rs = statement.executeQuery(sql);
		System.out.println("rs表已创建");
		if (judge) {
			while (rs.next()) {
				if (email.equals(rs.getString("email"))) {
					if (password.equals(rs.getString("password")))
						return 0;
					else
						return 1;
				}
			}
			return 2;
		} else {
			while (rs.next()) {
				if (email.equals(rs.getString("email")))
					return 1;
			}
			return 0;
		}
	}

	/*
	*名称：会议新建函数
	*描述：新建会议
	*参数：int state, String begintime, String endtime, String place, String name, String topic,
			String content, String host, int PeopleNum, int ArrivalNum, String FileUrl
	*返回类型：int
	*作者：周于楷
	*/
	public int insertMeeting(int state, String begintime, String endtime, String place, String name, String topic,
			String content, String host, int PeopleNum, int ArrivalNum, String FileUrl) throws SQLException {
		String sql = "SELECT id FROM MeetingTable ";
		rs = statement.executeQuery(sql);
		int id = 0;
		while (rs.next()) {
			id = rs.getInt("id");
		}
		id = id + 1;
		sql = "INSERT INTO MeetingTable(id,begintime,endtime,place,name,topic,content,host,state,PeopleNum,ArrivalNum,FileUrl,Assessment)"
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.setString(2,begintime);
		pstmt.setString(3,endtime);
		pstmt.setString(4, place);
		pstmt.setString(5, name);
		pstmt.setString(6, topic);
		pstmt.setString(7, content);
		pstmt.setString(8, host);
		pstmt.setInt(9, state);
		pstmt.setInt(10, PeopleNum);
		pstmt.setInt(11, ArrivalNum);
		pstmt.setString(12, FileUrl);
		pstmt.setInt(13, 0);
		pstmt.addBatch();
		pstmt.clearParameters();
		pstmt.executeBatch();
		pstmt.clearBatch();

		return id;
	}

	/*
	*名称：会议删除函数
	*描述：会议删除
	*参数：Date time, String name
	*返回类型：void
	*作者：周于楷
	*/
	public void deleteMeeting(Date time, String name) throws SQLException {
		String sql = "DELETE FROM MeetingTable WHERE begintime = " + time + " And name = " + name;
		statement.execute(sql);
	}
	/*
	*名称：会议删除函数
	*描述：会议删除
	*参数：int mid
	*返回类型：void
	*作者：周于楷
	*/
		public void deleteMeeting(int mid) throws SQLException {
			String sql = "DELETE FROM MeetingTable WHERE id = " + mid ;
			statement.execute(sql);
			deletePeople(mid);
		}
		/*
		*名称：会议搜索函数
		*描述：搜索某人主办的会议
		*参数：String host
		*返回类型：ResultSet
		*作者：周于楷
		*/
	public ResultSet searchMeeting(String host) throws SQLException {
		String sql = "SELECT * FROM MeetingTable WHERE host = '" + host + "'";
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
	*名称：未提交会议搜索函数
	*描述：搜索某人主办的未提交会议
	*参数：String host
	*返回类型：ResultSet
	*作者：周于楷
	*/
	public ResultSet searchChangableMeeting(String host) throws SQLException {
		String sql = "SELECT * FROM MeetingTable WHERE state = 0  And host = '" + host + "'";
		rs = statement.executeQuery(sql);
		return rs;
	}

	/*
	*名称：相同会议判断函数
	*描述：判断是否有相同的会议
	*参数：Date time, String name
	*返回类型：boolean
	*作者：周于楷
	*/
	public boolean searchMeeting(Date time, String name) throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String sql = "SELECT * FROM MeetingTable WHERE begintime = '" + sdf.format(time) + "' AND name = '" + name
				+ "'";
		System.out.println(sql);
		rs = statement.executeQuery(sql);
		boolean judge = true;
		while (rs.next())
			judge = false;
		return judge;

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
	*名称：会议评估状态更新函数
	*描述：更新会议评估状态
	*参数：int id
	*返回类型：void
	*作者：周于楷
	*/
	public void updateAssessment(int id) throws SQLException {
		String sql = "UPDATE MeetingTable SET Assessment = 1 WHERE   id = '" + id + "'";
		System.out.println(sql);
		statement.executeUpdate(sql);

	}

	/*
	*名称：参会人员新建函数	
	*描述：新建参会人员
	*参数：int id, ArrayList<InvitedPeople> people
	*返回类型：void
	*作者：周于楷
	*/
	public void insertPeople(int id, ArrayList<InvitedPeople> people) throws SQLException {
		System.out.println("insertPeople已进入");
		Iterator<InvitedPeople> it = people.iterator();
		while (it.hasNext()) {
			InvitedPeople p = it.next();
			System.out.println(p.getName() + "+" + p.getEmail());

			String sql = "INSERT INTO PeopleTable(Mid,uid,TOF,Email,PhoneNum)values(?,?,?,?,?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, p.getName());
			pstmt.setBoolean(3, false);
			pstmt.setString(4, p.getEmail());
			pstmt.setString(5, p.getPhoneNumber());
			pstmt.addBatch();
			pstmt.clearParameters();
			pstmt.executeBatch();
			pstmt.clearBatch();

		}
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
	*名称：参会人员删除函数	
	*描述：删除某id会议的参会人员
	*参数：int mid
	*返回类型：void
	*作者：周于楷
	*/
	public void deletePeople(int mid) throws SQLException {
		String sql = "delete FROM PeopleTable WHERE mid = '" + mid + "'";
		statement.execute(sql);
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

}
