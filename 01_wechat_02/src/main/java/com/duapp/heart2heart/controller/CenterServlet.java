package com.duapp.heart2heart.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.alibaba.fastjson.JSON;
import com.duapp.heart2heart.utils.EmailUtils;
import com.duapp.heart2heart.utils.SignUtil;

public class CenterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5996616850731271868L;

	/**
	 * Constructor of the object.
	 */
	public CenterServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 微信加密签名  
	    String signature = request.getParameter("signature");  
	    // 时间戮  
	    String timestamp = request.getParameter("timestamp");  
	    // 随机数  
	    String nonce = request.getParameter("nonce");  
	    // 随机字符串  
	    String echostr = request.getParameter("echostr");   
	      
	    PrintWriter out = response.getWriter();  
	    // 通过检验 signature 对请求进行校验，若校验成功则原样返回 echostr，表示接入成功，否则接入失败  
        if(SignUtil.checkSignature(signature, timestamp, nonce)){  
        	out.print(echostr);  
        }  
  
       out.close();  
       out = null;
       
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			EmailUtils.sendEmail("269363194@qq.com", "I come in");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SAXReader saxReader = new SAXReader();
		InputStream inputStream = null;
		try {
			inputStream = request.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//
		Document document = null;
		try {
			document = saxReader.read(inputStream);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = document.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> elements = root.elements();
		Map<String, String> map = new HashMap<>();
		for (Element element : elements) {
			map.put(element.getName(), element.getText());
		}
		String receiveData = JSON.toJSONString(map);
		try {
			EmailUtils.sendEmail("269363194@qq.com", receiveData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().print(receiveData);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
