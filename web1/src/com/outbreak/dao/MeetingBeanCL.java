package com.outbreak.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.outbreak.entity.MeetingBean;
import com.outbreak.util.DBConnect;
public class MeetingBeanCL {
	public DBConnect db;
	
	public MeetingBeanCL() {
		db=new DBConnect();
		try {
			db.connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//���鴴����j=trueʱΪ�ύ��j=falseʱΪ����ݸ�
	public boolean create(MeetingBean mb,boolean j) {
		boolean judge = true;
		int state=99;
		try {
			judge = db.searchMeeting(mb.getTime(),mb.getName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!judge) {
			return false;
		}
		if(j)
			state=1;
		else
			state=0;
		try {
			db.insertMeeting(state,mb.getTime(),mb.getPlace(),mb.getName(),mb.getContent()
					,mb.getHost(),mb.getPeopleNum(),mb.getArrivalNum());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	//�����ѯ
	public ResultSet search(String host) {
		try {
			System.out.println("search over");
			return db.searchMeeting(host);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
