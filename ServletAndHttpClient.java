/**
 * 
 */
package net.zhangwenbo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Iver3oN Zhang
 * @date 2016年3月24日
 * @email grepzwb@qq.com
 * ServletAndHttpClient.java
 * Impossible is nothing
 */
public class ServletAndHttpClient extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ServletAndHttpClient() {
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
		System.out.println("调用doget方法》》");
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("用户名和密码是："+name+">>"+password);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println("<HTML><BODY>");
		response.getWriter().println("请求登陆成功啊！！==> ");
		response.getWriter().println("</BODY></HTML>");
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
		doGet(request, response);
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
