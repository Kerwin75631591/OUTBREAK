package com.outbreak.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.outbreak.entity.MeetingBean;
import com.outbreak.util.DBConnect;


@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("GBK");
		response.setContentType("text/html;charset=GBK");
		response.setCharacterEncoding("GBK");
		
		String meetingName = request.getParameter("meetingName");
		String meetingTopic = request.getParameter("meetingTopic");
		String meetingData = request.getParameter("meetingData");
		String meetingPlace = request.getParameter("meetingPlace");
		String meetingContent = request.getParameter("meetingContent");
		String Users = request.getParameter("Users");
		
		System.out.println(meetingName);
		System.out.println(meetingTopic);
		System.out.println(meetingData);
		System.out.println(meetingPlace);
		System.out.println(meetingContent);
		System.out.println(Users);

		//新建会议实体
		MeetingBean mb = new MeetingBean();
		
		//设置会议信息
		mb.setName(request.getParameter("meetingName"));
		mb.setTopic(request.getParameter("meetingTopic"));
		System.out.println(request.getParameter("meetingBegintime"));
		System.out.println(request.getParameter("meetingEndtime"));
		if(!request.getParameter("meetingBegintime").equals("") && !request.getParameter("meetingEndtime").equals(""))
		{
			String begintime =request.getParameter("meetingData") + " " + request.getParameter("meetingBegintime");
			String endtime =request.getParameter("meetingData") + " " + request.getParameter("meetingEndtime");
			mb.setBegintime(begintime);
			mb.setEndtime(endtime);
		}
		else {
			mb.setBegintime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			mb.setEndtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		}
		mb.setContent(request.getParameter("meetingContent"));
		mb.setPlace(request.getParameter("meetingPlace"));
		if(!request.getParameter("Users").isEmpty()){
			//制作会议邀请名单
			String[] guests = request.getParameter("Users").split("-");
			for(int i = 0; i<guests.length-2; i+=3)
			{
				mb.addpeople(guests[i], guests[i+2],guests[i+1]);;
			}
			mb.setPeopleNum(guests.length / 3);
		}
		mb.setHost((String) request.getSession().getAttribute("sessionemail"));
		
		System.out.println(mb.getBegintime());
		System.out.println(mb.getEndtime());
		
		//上传至数据库
		DBConnect db = new DBConnect();
		try {
			db.connect();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int id=0;
		try {
			id = db.insertMeeting(0, mb.getBegintime(), mb.getEndtime(), mb.getPlace(), mb.getName(), mb.getTopic(), mb.getContent(), mb.getHost(), mb.getPeopleNum(), mb.getArrivalNum(), mb.getFileUrl());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			db.insertPeople(id, mb.getPeople());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.close();
		
		// 跳转到 会议管理页面
		response.getWriter().print("<script type=\"text/javascript\">alert('保存完成！');window.location='./JSP/MeetingManage.jsp'</script>");
	}

}
