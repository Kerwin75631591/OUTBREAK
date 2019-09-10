package com.outbreak.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.outbreak.util.DBConnect;

/**
 * Servlet implementation class EndMeetingServlet
 */
@WebServlet("/EndMeetingServlet")
public class EndMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EndMeetingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		response.setContentType("GBK");
		response.setCharacterEncoding("GBK");
		
		int mid = Integer.valueOf(request.getParameter("mid"));
		boolean isAssessment = Boolean.valueOf(request.getParameter("isAssessment"));
		
		
		try {
			DBConnect db = new DBConnect();
			db.connect();
			System.out.print("here");
			db.updateMeeting(mid, 4);
			if(isAssessment)
				db.updateAssessment(mid);
			db.close();
			request.getSession().setAttribute("sessionemail", request.getParameter("email")); //保存邮箱
			response.getWriter().print("<script type=\"text/javascript\">alert('会议结束!');window.location='./JSP/MeetingManage.jsp'</script>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}


	
}
