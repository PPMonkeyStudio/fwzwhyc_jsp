package org.pxxy.web.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.pxxy.domain.User;
import org.pxxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("struts-default")
@Namespace("/")
@Controller("userAction")  //创建对象
@Scope("prototype")    //多实例方式创建对象
public class UserAction extends ActionSupport implements ModelDriven<User>{
	private User user = new User();
	@Autowired  //注入
	private UserService userService;
	//后台查询信息类别数据
		@Action(value="login",results={@Result(name="success",location="/admin/main.jsp"),@Result(name="fail",location="/login.jsp")})
		public String login(){
			String page = "fail";			
			User user = null;
			//System.out.println(this.getModel().getUserName()+"--"+this.getModel().getPassword());
			user = userService.login(this.getModel());
			if (user != null) {
				//System.out.println(user.getUserName()+"--"+user.getPassword());
				if (user.getPassword().endsWith(this.user.getPassword())) {
					page = "success";
					HttpServletRequest request = ServletActionContext.getRequest();
					HttpSession session = request.getSession();
					if (session.getAttribute("userName") != null) {
						session.removeAttribute("userName");
					}
					session.setAttribute("userName", user.getUserName());
				} else {
					HttpServletRequest request = ServletActionContext.getRequest();
					request.setAttribute("msg", "用户名或密码错误!");
				}
			}else{
				HttpServletRequest request = ServletActionContext.getRequest();
				request.setAttribute("msg", "用户名或密码错误!");
			}
			return page;
		}
		
		@Action(value="logout")
		public String logout(){
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			if (session.getAttribute("userName") != null) {
				session.removeAttribute("userName");
			}
			
			HttpServletResponse response = (HttpServletResponse) ServletActionContext.getResponse();
			PrintWriter out=null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("<script language=javascript>");
			out.print("top.location.href='"+request.getContextPath()+"/login.jsp'");
			out.print("</script>");
			
			return null;
		}
		
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	
	

}
