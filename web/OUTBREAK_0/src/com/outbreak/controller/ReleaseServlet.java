package com.outbreak.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.outbreak.entity.MeetingBean;
import com.outbreak.util.DBConnect;

/**
 * Servlet implementation class ReleaseServlet
 */
@WebServlet("/ReleaseServlet")
public class ReleaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIRECTORY = "upload";	// 上传文件存储目录
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 20; // 10MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 30; // 30MB
    private String filePath;
    private Map<String,String> map = new HashMap<String,String>(); 
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @author HuYu
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
		// 检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 如果不是则停止
        	System.out.println("1");
        	response.getWriter().print("<script type=\"text/javascript\">alert('错误信息: Error: 表单必须包含 enctype=multipart/form-data');window.location='./JSP/MeetingCreate.jsp'</script>");
        }
 
        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);
         
        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // 中文处理
        upload.setHeaderEncoding("GBK"); 

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        String uploadPath = request.getServletContext().getRealPath("./") + UPLOAD_DIRECTORY;
       
        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
        	System.out.println("create new direction!");
            uploadDir.mkdir();
        }
 
        try {
            // 解析请求的内容提取文件数据
            List<FileItem> formItems = upload.parseRequest(request);
 
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                	//处理form中非文件内容
                	if (item.isFormField())
                	{
                		String name = item.getFieldName(); 
                		String value = item.getString(); 
                		System.out.println(name+"+"+value);
                		map.put(name, value); 
                	}
                    // 处理不在表单中的字段（文件内容）
                	else{
                        String fileName = new File(item.getName()).getName();
                        filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        // 在控制台输出文件的上传路径
                        System.out.println(filePath);
                        // 保存文件到硬盘
                        item.write(storeFile);
                    }
                }
            }
        } catch (Exception ex) {
        	System.out.println("2");
        	response.getWriter().print("<script type=\"text/javascript\">alert(错误信息:" + ex.getMessage() + ");window.location='./JSP/MeetingCreate.jsp'</script>");
        }
        
        //在控制台迭代输出文本内容
        Iterator<String> iterator = map.keySet().iterator();
		while(iterator.hasNext())
		{
			String key = iterator.next();
			System.out.println(key + "\t" + map.get(key));
		}
		
		//新建会议实体
		MeetingBean mb = new MeetingBean();
		
		String begintime = map.get("meetingData") + " " + map.get("meetingBegintime");
		String endtime = map.get("meetingData") + " " + map.get("meetingBegintime");
		mb.setBegintime(begintime);
		mb.setEndtime(endtime);

		//设置会议内容
		mb.setName(new String(map.get("meetingName").getBytes("iso-8859-1"), "GBK"));
		mb.setTopic(new String(map.get("meetingTopic").getBytes("iso-8859-1"), "GBK"));
		mb.setContent(new String(map.get("meetingContent").getBytes("iso-8859-1"), "GBK"));
		mb.setPlace(new String(map.get("meetingPlace").getBytes("iso-8859-1"), "GBK"));
		mb.setFileUrl(filePath);
		mb.setHost((String) request.getSession().getAttribute("sessionemail"));
		
		//制作会议邀请名单
		String[] guests = (new String(map.get("Users").getBytes("iso-8859-1"), "GBK")).split("-");
		for(int i = 0; i<guests.length-2; i+=3)
		{
			mb.addpeople(guests[i], guests[i+2],guests[i+1]);;
		}
		mb.setPeopleNum(guests.length / 3);
		
		DBConnect db=new DBConnect();
		//检查是否有同名同时同地会议
		
		try {
			db.connect();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int id=0;
		try {
			id = db.insertMeeting(1, mb.getBegintime(), mb.getEndtime(), mb.getPlace(), mb.getName(), mb.getTopic(), mb.getContent(), mb.getHost(), mb.getPeopleNum(), mb.getArrivalNum(), mb.getFileUrl());
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
		response.getWriter().print("<script type=\"text/javascript\">alert('发布完成！');window.location='./JSP/MeetingManage.jsp'</script>");
	}

}
