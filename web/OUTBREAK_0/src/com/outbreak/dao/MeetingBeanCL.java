package com.outbreak.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.outbreak.entity.MeetingBean;
import com.outbreak.util.DBConnect;
public class MeetingBeanCL {
	public DBConnect db;
	
	/*
	*名称：构造函数
	*描述：初始化MeetingBeanCL对象
	*参数：void
	*返回类型：void
	*作者：周于楷
	*/
	public MeetingBeanCL() {
		db=new DBConnect();
		try {
			db.connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	*名称：会议创建函数
	*描述：新建会议
	*参数：MeetingBean mb,boolean j，j=true时为提交，j=false时为保存草稿
	*返回类型：boolean
	*作者：周于楷
	*/
	public boolean create(MeetingBean mb,boolean j) {
		boolean judge = true;
		int state=99;
		/*try {
			judge = db.searchMeeting(mb.getBegintime(),mb.getName());
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		if (!judge) {
			return false;
		}
		if(j)
			state=1;
		else
			state=0;
		try {
			int id=db.insertMeeting(state,mb.getBegintime(),mb.getEndtime(),mb.getPlace(),mb.getName(),mb.getTopic(),mb.getContent()
					,mb.getHost(),mb.getPeopleNum(),mb.getArrivalNum(),mb.getFileUrl());
			db.insertPeople(id,mb.getPeople());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/*
	*名称：会议搜索函数
	*描述：搜索某人主持的会议
	*参数：String host，host为主办方邮箱
	*返回类型：ResultSet
	*作者：周于楷
	*/
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
