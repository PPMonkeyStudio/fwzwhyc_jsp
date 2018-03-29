package org.pxxy.utils;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
public class LoginFilter implements Filter {
	@Override
	public void destroy() {
		// TODO Auto-generated method stub		
	}
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		/*
		 * 1. 从session中获取用户信息
		 * 2. 判断如题session中存在用户信息，放行！
		 * 3. 否则，保存错误信息，转发到login.jsp显示
		 */
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String userName = (String)httpRequest.getSession().getAttribute("userName");
		if(userName != null) {
			chain.doFilter(request, response);//放行！
		} else {
			httpRequest.setAttribute("msg", "您还没有登录！");			
			PrintWriter out = response.getWriter();
			out.print("<script language=javascript>");
			out.print("top.location.href='"+httpRequest.getContextPath()+"/login.jsp'");
			out.print("</script>");			
			/*httpRequest.getRequestDispatcher("/login.jsp").forward(httpRequest, response);*/
		}
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub		
	}
}
