/**
 * @author huyu
 * @createTime 2019/09/03
 * @Updata 2019/09/08
 */
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
 * 名称：结束会议Servlet类
 * 描述：用来响应MeetingManage.jsp中“结束会议”按钮
 * 作者：胡昱
 */
@WebServlet("/EndMeetingServlet")
public class EndMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 名称：构造函数
	 * 描述：默认的构造函数
	 * 参数：void
	 * 返回类型：void
	 * 作者：胡昱
	 * @see HttpServlet#HttpServlet()
	 */
    public EndMeetingServlet() {
        super();
    }

	/**
	 * 名称：处理Get形式消息的函数
	 * 描述：将从MeetingManage.jsp中以Get形式传来的数据交给doPost(HttpServletRequest, HttpServletResponse)函数处理
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
	 * 描述：用来处理MeetingManage.jsp传过来的请求
	 * 参数：HttpServletRequest request, HttpServletResponse response
	 * 返回类型：void
	 * 作者：胡昱
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置传输数据时的编码格式
		request.setCharacterEncoding("GBK");
		response.setContentType("GBK");
		response.setCharacterEncoding("GBK");
		
		int mid = Integer.valueOf(request.getParameter("mid"));
		boolean isAssessment = Boolean.valueOf(request.getParameter("isAssessment"));
		
		//将数据库中需要结束的会议的状态改为“已结束”，如果需要进行会议评估的则将Assessment置为1
		try {
			DBConnect db = new DBConnect();
			db.connect();
			db.updateMeeting(mid, 4);
			if(isAssessment)
				db.updateAssessment(mid);
			db.close();
			request.getSession().setAttribute("sessionemail", request.getParameter("email")); //��������
			response.getWriter().print("<script type=\"text/javascript\">alert('�������!');window.location='./JSP/MeetingManage.jsp'</script>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
