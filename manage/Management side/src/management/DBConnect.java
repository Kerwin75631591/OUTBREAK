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
	*���ƣ����ݿ���������
	*��������ʼ�����ݿ�����
	*������void
	*�������ͣ�void
	*���ߣ����ڿ�
	*/
	public DBConnect() {

		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			System.out.println("�޷��ҵ�������");
		}
	}

	/*
	*���ƣ����ݿ��ʼ������
	*��������ʼ�����ݿ��ʽ
	*������void
	*�������ͣ�void
	*���ߣ����ڿ�
	*/
	public void initiazation() throws SQLException {
		try {
			// �����������ݿ�
			String dbURL = "jdbc:mysql://localhost:3306/"
					+ "MyDB?user=root&password=749847569&serverTimezone=GMT%2B8&useSSL=false";
			connection = DriverManager.getConnection(dbURL);
			statement = connection.createStatement();
			// �½����ݿ�
			statement.executeUpdate("create database UserDB");
			// ���½������ݿ�
			statement.close();
			connection.close();
			Class.forName("com.mysql.jdbc.Driver");
			dbURL = "jdbc:mysql://localhost:3306/"
					+ "UserDB?user=root&password=749847569&serverTimezone=GMT%2B8&useSSL=false";
			connection = DriverManager.getConnection(dbURL);
			statement = connection.createStatement();

			// �����û�����¼ id�����䣬���룬��ϵ��ʽ�����֣���ַ��
			statement.executeUpdate(
					"create table UserTable(id integer(5),email varchar(20),password varchar(20),phoneNumber varchar(11), name varchar(20), address varchar(20))");

			// �����������¼ id�����䣬���룬��ϵ��ʽ�����֣���ַ��
			statement.executeUpdate(
					"create table MeetingTable(id integer(5),time date,place varchar(20),name varchar(20), "
							+ "content varchar(20), host varchar(20), state integer(5), PeopleNum integer(5),ArrivalNum integer(5))");

			// ������Ա����¼ ����id����Աid���Ƿ�μ�
			statement.executeUpdate("create table PeopleTable(Mid integer(5),Uid integer(5),TOF tinyint)");

		} catch (ClassNotFoundException e) {
			System.out.println("�޷��ҵ�������");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	*���ƣ����ݿ����Ӻ���
	*�������������ݿ�
	*������void
	*�������ͣ�void
	*���ߣ����ڿ�
	*/
	public void connect() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String dbURL = "jdbc:mysql://localhost:3306/"
					+ "UserDB?user=root&password=749847569&serverTimezone=GMT%2B8&useSSL=false";
			connection = DriverManager.getConnection(dbURL);
			statement = connection.createStatement();
			System.out.println("���ݿ�������");
		} catch (ClassNotFoundException e) {
			System.out.println("�޷��ҵ�������");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("���ݿ��޷�����");
		}
	}

	/*
	*���ƣ��½��û�����
	*�����������û��������ݿ�
	*������String email, String password, String phoneNumber, String name, String address
	*�������ͣ�void
	*���ߣ����ڿ�
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
	*���ƣ��û�ɾ������
	*������ɾ���û�
	*������String email
	*�������ͣ�void
	*���ߣ����ڿ�
	*/
	public void deleteUser(String email) throws SQLException {
		String sql = "DELETE * FROM UserTable WHERE email = " + email;
		statement.execute(sql);
	}

	/*
	*���ƣ��û�ע�ắ��
	*�������Զ�ע���û�
	*������String email
	*�������ͣ�void
	*���ߣ����ڿ�
	*/
	public boolean RegistUser(String email, String name, String phoneNum) throws SQLException {
		String sql = "SELECT*FROM UserTable WHERE email = '" + email + "'";
		rs = statement.executeQuery(sql);
		System.out.println("rs���Ѵ���");
		if(rs!=null)
			return true;
		else {
			insertUser(email, "outbreak123", phoneNum, name, null);
			return false;
		}
	}

	/*
	*���ƣ�������������
	*�������������д���˵Ļ���
	*����: void
	*�������ͣ�ResultSet
	*���ߣ����ڿ�
	*/
	public ResultSet searchMeeting() throws SQLException {
		String sql = "SELECT * FROM MeetingTable WHERE state = 1";
		rs = statement.executeQuery(sql);
		return rs;
	}
	/*
	*���ƣ�������������
	*����������id�Ļ���
	*������int id
	*�������ͣ�ResultSet
	*���ߣ����ڿ�
	*/
	public ResultSet searchMeeting(int id) throws SQLException {
		String sql = "SELECT * FROM MeetingTable WHERE id = '" + id + "'";
		rs = statement.executeQuery(sql);
		return rs;
	}

	/*
	*���ƣ�����״̬���º���
	*���������»���״̬
	*������int id, int state
	*�������ͣ�void
	*���ߣ����ڿ�
	*/
	public void updateMeeting(int id, int state) throws SQLException {
		String sql = "UPDATE MeetingTable SET state = " + state + " WHERE   id = '" + id + "'";
		System.out.println(sql);
		statement.executeUpdate(sql);

	}
	/*
	*���ƣ��λ���Ա��������	
	*����������ĳid����Ĳλ���Ա
	*������int mid
	*�������ͣ�ResultSet
	*���ߣ����ڿ�
	*/
	public ResultSet searchPeople(int mid) throws SQLException {
		String sql = "SELECT * FROM PeopleTable WHERE mid = '" + mid + "'";
		rs = statement.executeQuery(sql);
		return rs;
	}

	/*
	*���ƣ�ȫ����Ϣ���ͺ���	
	*����������ȫ����Ϣ
	*������String message
	*�������ͣ�void
	*���ߣ����ڿ�
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
	*���ƣ����ݿ����ӹرպ���
	*�������ر����ݿ�����
	*������void
	*�������ͣ�void
	*���ߣ����ڿ�
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
