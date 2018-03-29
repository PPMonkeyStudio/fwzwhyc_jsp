package org.pxxy.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.pxxy.domain.Category;
import org.pxxy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;

@ParentPackage("struts-default")
@Namespace("/")
@Controller("categoryAction")  //创建对象
@Scope("prototype")    //多实例方式创建对象
public class CategoryAction extends ActionSupport implements ModelDriven<Category>{
	private Category category = new Category();	
		
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Autowired  //注入
	private CategoryService categoryService;
	private List<Category> list =null;

	public List<Category> getList() {
		return list;
	}

	//后台查询信息类别数据
	@Action(value="/admin/findAllCategory",results={@Result(name="success",location="/admin/category/list.jsp")})
	public String findAllCategory(){
		try {
			list = categoryService.findAllCategory();
		}catch(Exception e){
			e.printStackTrace();
		}	
		return "success";
	}
	
	@Action(value="/admin/findAllCategoryForDrop")
	public String findAllCategoryForDrop(){
		try {
			List<Category> list = categoryService.findAllCategory();
			HttpServletResponse response = ServletActionContext.getResponse(); //响应对象
			response.setContentType("text/html;charset=UTF-8"); //告知浏览器使用UTF-8编码
			PrintWriter out = response.getWriter();
			String json = JSONArray.fromObject(list).toString();
			System.out.println(json);
			out.write(json);
			//System.out.println("c......");
			//[{"cid":"1","cname":"新闻资讯"}，{"cid":"2","cname":"通知公告"}。。。]
			/*String json = "[";
			for (int i = 0; i < list.size(); i++) {
				Category c = list.get(i);
				if(i>0){
					json+=",";
				}
				json +="{\"cid\":'"+c.getCid()+"','cname':'"+c.getCname()+"'}";
			}
			json+="]";*/
			
//			String json = JSONArray.fromObject(list).toString();
			
			//System.out.println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Action(value="findNaviCategory")
	public String findNaviCategory(){
		try {
			List<Category> list = categoryService.findNaviCategory();
			
			HttpServletResponse response = ServletActionContext.getResponse(); //响应对象
			response.setContentType("text/html;charset=UTF-8"); //告知浏览器使用UTF-8编码
			
			PrintWriter out = response.getWriter();
			String json = JSONArray.fromObject(list).toString();
			out.write(json);
			//System.out.println("c......");
			//[{"cid":"1","cname":"新闻资讯"}，{"cid":"2","cname":"通知公告"}。。。]
			/*String json = "[";
			for (int i = 0; i < list.size(); i++) {
				Category c = list.get(i);
				if(i>0){
					json+=",";
				}
				json +="{\"cid\":'"+c.getCid()+"','cname':'"+c.getCname()+"'}";
			}
			json+="]";*/
			
//			String json = JSONArray.fromObject(list).toString();
			
			//System.out.println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	//添加分类
	@Action(value="/admin/addCategory",results={@Result(name="success",location="/admin/findAllCategory",type = "redirect")})
	public String addCategory() {
		try {
			categoryService.addCategory(category);
		}catch(Exception e){
			e.printStackTrace();
		}	
		return "success";
	}
	
	//删除某个分类 
	@Action(value="/admin/delCategory",results={@Result(name="success",location="/admin/findAllCategory",type = "redirect")})
	public String delCategory(){		
		try {
			categoryService.delCategory(category);
		}catch(Exception e){
			e.printStackTrace();
		}	
		return "success";
	}

	//根据cid查询指定类别对象
	@Action(value="/admin/editCategory",results={@Result(name="success",location="/admin/category/edit.jsp")})
	public String editCategory(){		
		try {
			category = categoryService.findCategoryByCid(category.getCid());
			this.setCategory(category);
			//ActionContext.getContext().getValueStack().push(category);			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");//告知浏览器使用什么编码解析文本
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	//更新类别
	@Action(value="/admin/updateCategory",results={@Result(name="success",location="/admin/findAllCategory",type = "redirect")})
	public String updateCategory(){
		try {
			categoryService.updateCategory(category);
		}catch(Exception e){
			e.printStackTrace();
		}	
		return "success";
	}
	
	@Override
	public Category getModel() {
		return category;
	}
	
	
}
