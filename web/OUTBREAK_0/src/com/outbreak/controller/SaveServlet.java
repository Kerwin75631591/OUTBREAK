/**
 * @author huyu
 * @createTime 2019/08/28
 * @Updata 2019/09/04
 */
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

/**
 * 名称：保存草稿Servlet类
 * 描述：用来响应MeetingCreate.jsp中“保存草稿”按钮
 * 作者：胡昱
 */
@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 名称：处理Get形式消息的函数
	 * 描述：将从MeetingCreate.jsp中以Get形式传来的数据交给doPost(HttpServletRequest, HttpServletResponse)函数处理
	 * 参数：HttpServletRequest request, HttpServletResponse response
	 * 返回类型：void
	 * 作者：胡昱
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 名称：处理Post形式消息的函数
	 * 描述：用来处理MeetingCreate.jsp传过来的以enctype=multipart/form-data形式的请求，将所填写的会议信息保存至数据库以便用户下次编辑
	 * 参数：HttpServletRequest request, HttpServletResponse response
	 * 返回类型：void
	 * 作者：胡昱
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
