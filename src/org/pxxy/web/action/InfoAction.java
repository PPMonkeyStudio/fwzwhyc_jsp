package org.pxxy.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.pxxy.domain.Category;
import org.pxxy.domain.Info;
import org.pxxy.domain.PageBean;
import org.pxxy.service.CategoryService;
import org.pxxy.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;

@ParentPackage("struts-default")
@Namespace("/")
@Controller("infoAction")  //创建对象
@Scope("prototype")    //多实例方式创建对象
public class InfoAction extends ActionSupport implements ModelDriven<Info>{
	private Info info=new Info();
	private String keywords;
	private List<Category> categorylist;
	@Autowired  //注入
	private CategoryService categoryService;			

	public Info getInfo() {
		return info;
	}	
	public void setInfo(Info info) {
		this.info = info;
	}	
	
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public List<Category> getCategorylist() {
		return categorylist;
	}
	public void setCategorylist(List<Category> categorylist) {
		this.categorylist = categorylist;
	}

	private List<Info> fyzxInfos = null;
	private List<Info> xsjlInfos = null;
	private List<Info> fyjjInfos = null;
	private List<Info> hzptInfos = null;
	private List<Info> fycrInfos = null;
	private List<Info> fyjtInfos = null;
	private List<Info> findInfosByPage=null;	
	
	public List<Info> getFindInfosByPage() {
		return findInfosByPage;
	}
	public List<Info> getFyzxInfos() {
		return fyzxInfos;
	}
	public List<Info> getXsjlInfos() {
		return xsjlInfos;
	}
	public List<Info> getHzptInfos() {
		return hzptInfos;
	}
	public List<Info> getFycrInfos() {
		return fycrInfos;
	}
	public List<Info> getFyjtInfos() {
		return fyjtInfos;
	}
	public List<Info> getFyjjInfos() {
		return fyjjInfos;
	}
	@Autowired
	private InfoService infoService;
	
	private List<Info> list =null;
	private List<Info> vvv;
	public List<Info> getList() {
		return list;
	}

	@Action(value="/admin/findAllInfo",results={@Result(name="success",location="/admin/info/list.jsp")})
	public String findAllInfo(){
		try {			
			list=infoService.findAllInfo();	
			//System.out.println(list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	@Action(value="/admin/findAllInfoBy",results={@Result(name="success",location="/admin/info/list.jsp")})
	public String findAllInfoBy(){
		try {			
			list=infoService.findAllInfo(this.getKeywords().trim());	
			//System.out.println(list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	//查询首页信息数据
	@Action(value="findInfos",results={@Result(name="findInfos",location="/index.jsp")})
	public String findInfos(){		
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Info> fyzxInfos = infoService.findFyzxInfos();
		List<Info> xsjlInfos = infoService.findXsjlInfos();
		//Info xsjlInfo = infoService.findXsjlInfo();
		List<Info> fyjjInfos = infoService.findFyjjInfos();
		List<Info> hzptInfos = infoService.findHzptInfos();
		List<Info> fycrInfos = infoService.findFycrInfos();
		List<Info> fyjtInfos = infoService.findFyjtInfos();
		request.setAttribute("fyzxInfos", fyzxInfos);
		request.setAttribute("xsjlInfos", xsjlInfos);
		//request.setAttribute("xsjlInfo", xsjlInfo);
		request.setAttribute("fyjjInfos", fyjjInfos);
		request.setAttribute("hzptInfos", hzptInfos);
		request.setAttribute("fycrInfos", fycrInfos);
		request.setAttribute("fyjtInfos", fyjtInfos);
		return "findInfos";
	}
	

		

		private int currentPage=1; //当前页
		private int pageSize = 10;//默认每页显示条数
		private int cid;  //商品类别编号
		private PageBean<Info> pb;  // ${pb}
		public int getCurrentPage() {
			return currentPage;
		}
		public void setCurrentPage(int currentPage) {
			this.currentPage = currentPage;
		}
		public int getPageSize() {
			return pageSize;
		}
		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}	
		public int getCid() {
			return cid;
		}
		public void setCid(int cid) {
			this.cid = cid;
		}
		public PageBean<Info> getPb() {
			return pb;
		}
		public void setPb(PageBean<Info> pb) {
			this.pb = pb;
		}
		
		//根据类别Id查询分页信息数据
		@Action(value="findInfosByCid",results={@Result(name="findInfosByCid",location="/infolist.jsp")})
		public String findInfosByCid(){
			
			pb = infoService.findInfosByCid(currentPage,pageSize,cid);
			this.setPb(pb);
			Category category = categoryService.findCategoryByCid(this.cid);
			ActionContext.getContext().put("category", category);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");//告知浏览器使用什么编码解析文本
			return "findInfosByCid";
		}			
		
		//根据类别Id查询分页信息数据
		@Action(value="/admin/findInfosByPage",results={@Result(name="findInfosByPage",location="/admin/info/list.jsp")})
		public String findInfosByPage(){
			if(keywords !=null){
				keywords=keywords.trim();
			}
			pb = infoService.findInfosByPage(currentPage,pageSize,keywords);
			this.setPb(pb);			
			return "findInfosByPage";
		}					

	// 添加信息
	@Action(value = "/admin/addInfo",results={@Result(name="success",location="/admin/findInfosByPage",type = "redirect")})
	public String addInfo() {
		Date d = new Date();  
        info.setPublishTime(d);
        try {
			infoService.addInfo(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "success";			
	}
	
	//删除信息
	@Action(value = "/admin/delInfo",results={@Result(name="success",location="/admin/findInfosByPage",type = "redirect")})
	public String delInfo(){
		try {
			infoService.delInfo(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return "success";	
	}

	//根据cid查询指定信息对象
	@Action(value="/admin/editInfo",results={@Result(name="editInfo",location="/admin/info/edit.jsp")})
	public String editInfo(){
		info = infoService.findInfoByInfoId(info.getInfoId());
		this.setInfo(info);
		this.setCategorylist(categoryService.findAllCategory());
		return "editInfo";
	}
	
		//根据cid查询指定信息对象
	  @Action(value="findInfoByInfoId",results={@Result(name="findInfoByInfoId",location="/detail.jsp"),@Result(name="error",location="/error.jsp")})
	
	public String findInfoByInfoId(){
			info = infoService.findInfoByInfoId(info.getInfoId());
			ActionContext.getContext().getValueStack().push(info);
			Category category = categoryService.findCategoryByCid(info.getCategory().getCid());
			ActionContext.getContext().put("category", category);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");//告知浏览器使用什么编码解析文本
			
		return "findInfoByInfoId";
	}
	
	//更新信息
	  @Action(value="/admin/updateInfo",results={@Result(name="updateInfo",location="/admin/findInfosByPage",type = "redirect")})	
	public String updateInfo(){
		Date d = new Date();  
        info.setPublishTime(d);
        //System.out.println(info.getInfoId());
		 try {
			 infoService.updateInfo(info);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return "updateInfo";
	}
		
	@Override
	public Info getModel() {
		return info;
	}
	
	

}
