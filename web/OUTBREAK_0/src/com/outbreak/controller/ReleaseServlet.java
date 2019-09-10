/**
 * @author huyu
 * @createTime 2019/08/28
 * @Updata 2019/09/03
 */
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
 * 名称：发布会议Servlet类
 * 描述：用来响应MeetingCreate.jsp中“发布会议”按钮（当用户上传文件时）
 * 作者：胡昱
 */
@WebServlet("/ReleaseServlet")
public class ReleaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIRECTORY = "upload";	// 涓婁紶鏂囦欢瀛樺偍鐩綍
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 20; // 10MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 30; // 30MB
    private String filePath;
    private Map<String,String> map = new HashMap<String,String>(); 
	
	
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
	 * 描述：用来处理MeetingCreate.jsp传过来的以enctype=multipart/form-data形式的请求，将所填写的会议资料以及资料保存至数据库以及服务器
	 * 参数：HttpServletRequest request, HttpServletResponse response
	 * 返回类型：void
	 * 作者：胡昱
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("GBK");
		response.setContentType("GBK");
		response.setCharacterEncoding("GBK");
		// 妫�娴嬫槸鍚︿负澶氬獟浣撲笂浼�
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 濡傛灉涓嶆槸鍒欏仠姝�
        	System.out.println("1");
        	response.getWriter().print("<script type=\"text/javascript\">alert('閿欒淇℃伅: Error: 琛ㄥ崟蹇呴』鍖呭惈 enctype=multipart/form-data');window.location='./JSP/MeetingCreate.jsp'</script>");
        }
 
        // 閰嶇疆涓婁紶鍙傛暟
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 璁剧疆鍐呭瓨涓寸晫鍊� - 瓒呰繃鍚庡皢浜х敓涓存椂鏂囦欢骞跺瓨鍌ㄤ簬涓存椂鐩綍涓�
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 璁剧疆涓存椂瀛樺偍鐩綍
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        // 璁剧疆鏈�澶ф枃浠朵笂浼犲��
        upload.setFileSizeMax(MAX_FILE_SIZE);
         
        // 璁剧疆鏈�澶ц姹傚�� (鍖呭惈鏂囦欢鍜岃〃鍗曟暟鎹�)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // 涓枃澶勭悊
        upload.setHeaderEncoding("GBK"); 

        // 鏋勯�犱复鏃惰矾寰勬潵瀛樺偍涓婁紶鐨勬枃浠�
        // 杩欎釜璺緞鐩稿褰撳墠搴旂敤鐨勭洰褰�
        String uploadPath = request.getServletContext().getRealPath("./") + UPLOAD_DIRECTORY;
       
        // 濡傛灉鐩綍涓嶅瓨鍦ㄥ垯鍒涘缓
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
        	System.out.println("create new direction!");
            uploadDir.mkdir();
        }
 
        try {
            // 瑙ｆ瀽璇锋眰鐨勫唴瀹规彁鍙栨枃浠舵暟鎹�
            List<FileItem> formItems = upload.parseRequest(request);
 
            if (formItems != null && formItems.size() > 0) {
                // 杩唬琛ㄥ崟鏁版嵁
                for (FileItem item : formItems) {
                	//澶勭悊form涓潪鏂囦欢鍐呭
                	if (item.isFormField())
                	{
                		String name = item.getFieldName(); 
                		String value = item.getString(); 
                		System.out.println(name+"+"+value);
                		map.put(name, value); 
                	}
                    // 澶勭悊涓嶅湪琛ㄥ崟涓殑瀛楁锛堟枃浠跺唴瀹癸級
                	else{
                        String fileName = new File(item.getName()).getName();
                        filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        // 鍦ㄦ帶鍒跺彴杈撳嚭鏂囦欢鐨勪笂浼犺矾寰�
                        System.out.println(filePath);
                        // 淇濆瓨鏂囦欢鍒扮‖鐩�
                        item.write(storeFile);
                    }
                }
            }
        } catch (Exception ex) {
        	System.out.println("2");
        	response.getWriter().print("<script type=\"text/javascript\">alert(閿欒淇℃伅:" + ex.getMessage() + ");window.location='./JSP/MeetingCreate.jsp'</script>");
        }
        
        //鍦ㄦ帶鍒跺彴杩唬杈撳嚭鏂囨湰鍐呭
        Iterator<String> iterator = map.keySet().iterator();
		while(iterator.hasNext())
		{
			String key = iterator.next();
			System.out.println(key + "\t" + map.get(key));
		}
		
		//鏂板缓浼氳瀹炰綋
		MeetingBean mb = new MeetingBean();
		
		String begintime = map.get("meetingData") + " " + map.get("meetingBegintime");
		String endtime = map.get("meetingData") + " " + map.get("meetingBegintime");
		mb.setBegintime(begintime);
		mb.setEndtime(endtime);

		//璁剧疆浼氳鍐呭
		mb.setName(new String(map.get("meetingName").getBytes("iso-8859-1"), "GBK"));
		mb.setTopic(new String(map.get("meetingTopic").getBytes("iso-8859-1"), "GBK"));
		mb.setContent(new String(map.get("meetingContent").getBytes("iso-8859-1"), "GBK"));
		mb.setPlace(new String(map.get("meetingPlace").getBytes("iso-8859-1"), "GBK"));
		mb.setFileUrl(filePath);
		mb.setHost((String) request.getSession().getAttribute("sessionemail"));
		
		//鍒朵綔浼氳閭�璇峰悕鍗�
		String[] guests = (new String(map.get("Users").getBytes("iso-8859-1"), "GBK")).split("-");
		for(int i = 0; i<guests.length-2; i+=3)
		{
			mb.addpeople(guests[i], guests[i+2],guests[i+1]);;
		}
		mb.setPeopleNum(guests.length / 3);
		
		DBConnect db=new DBConnect();
		//妫�鏌ユ槸鍚︽湁鍚屽悕鍚屾椂鍚屽湴浼氳
		
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
		
        // 璺宠浆鍒� 浼氳绠＄悊椤甸潰
		response.getWriter().print("<script type=\"text/javascript\">alert('鍙戝竷瀹屾垚锛�');window.location='./JSP/MeetingManage.jsp'</script>");
	}

}
