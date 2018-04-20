package org.pxxy.web.action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.pxxy.dao.CategoryDao;
import org.pxxy.dao.InfoDao;
import org.pxxy.domain.Category;
import org.pxxy.domain.Info;
import org.pxxy.domain.PageBean;
import org.pxxy.utils.Time;
import org.pxxy.utils.UUIDUtils;

@SuppressWarnings("serial")
public class InfoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String option = request.getParameter("option");

		switch (option) {
		case "findInfosByPage": {
			findInfosByPage(request, response);
			break;
		}
		case "addInfo": {
			addInfo(request, response);
			break;
		}
		case "delInfo": {
			delInfo(request, response);
			break;
		}
		case "toAddInfoPage": {
			toAddInfoPage(request, response);
			break;
		}
		case "editInfo": {
			editInfo(request, response);
			break;
		}
		case "getImg": {
			getImg(request, response);
			break;
		}
		case "updateInfo": {
			updateInfo(request, response);
			break;
		}
		case "findInfos": {
			findInfos(request, response);
			break;
		}
		case "findInfosByCid": {
			findInfosByCid(request, response);
			break;
		}
		case "findInfoByInfoId": {
			findInfoByInfoId(request, response);
			break;
		}

		}

	}

	/*
	 * 
	 */
	private int pageSize = 10;// 默认每页显示条数
	/*
	 * 
	 */

	private void findInfoByInfoId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InfoDao infoDao = new InfoDao();

		Info info = infoDao.findInfoByInfoId(Integer.parseInt(request.getParameter("infoId")));
		request.setAttribute("info", info);
		/*
		 * 
		 */
		CategoryDao categoryDao = new CategoryDao();
		Category category = categoryDao.findCategoryByCid(Integer.parseInt(request.getParameter("cid")));
		request.setAttribute("category", category);
		/*
		 * 
		 */
		request.getRequestDispatcher("/detail.jsp").forward(request, response);

	}

	/*
	 * 
	 */
	private void findInfosByCid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InfoDao infoDao = new InfoDao();
		PageBean<Info> pb = infoDao.findInfosByCid(Integer.parseInt(request.getParameter("currentPage")), pageSize,
				Integer.parseInt(request.getParameter("cid")));
		request.setAttribute("pb", pb);
		/*
		 * 
		 */
		CategoryDao categoryDao = new CategoryDao();
		Category category = categoryDao.findCategoryByCid(Integer.parseInt(request.getParameter("cid")));
		request.setAttribute("category", category);
		/*
		 * 
		 */
		request.getRequestDispatcher("/infolist.jsp").forward(request, response);
	}

	/*
	 * 获取首页栏目的新闻
	 */
	private void findInfos(HttpServletRequest request, HttpServletResponse response) {
		InfoDao infoDao = new InfoDao();
		/*
		 * 非遗资讯
		 */
		List<Info> fyzxInfos = infoDao.findFyzxInfos();
		request.setAttribute("fyzxInfos", fyzxInfos);
		/*
		 * 学术交流
		 */
		List<Info> xsjlInfos = infoDao.findXsjlInfos();
		request.setAttribute("xsjlInfos", xsjlInfos);
		/*
		 * 
		 */
		List<Info> fyjtInfos = infoDao.findFyjtInfos();
		request.setAttribute("fyjtInfos", fyjtInfos);
		/*
		 * 
		 */
		List<Info> fyjjInfos = infoDao.findFyjjInfos();
		request.setAttribute("fyjjInfos", fyjjInfos);
		/*
		 * 
		 */
		List<Info> hzptInfos = infoDao.findHzptInfos();
		request.setAttribute("hzptInfos", hzptInfos);
		/*
		 * 
		 */
		List<Info> fycrInfos = infoDao.findFycrInfos();
		request.setAttribute("fycrInfos", fycrInfos);
		/*
		 * 
		 */
	}

	//
	private void updateInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InfoDao infoDao = new InfoDao();
		/*
		 * 
		 */
		Info info = new Info();
		info.setInfoId(Integer.parseInt(request.getParameter("infoId")));
		info.setAuthor(request.getParameter("author"));
		info.setTitle(request.getParameter("title"));
		info.setContentTitle(request.getParameter("contentTitle"));
		info.setContentAbstract(request.getParameter("contentAbstract"));
		info.setContent(request.getParameter("content"));
		info.setPublishStatus(request.getParameter("publishStatus"));
		info.setPaiXu(Integer.parseInt(request.getParameter("paiXu")));
		info.setCid(Integer.parseInt(request.getParameter("cid")));
		infoDao.updateInfo(info);
		/*
		 * 
		 */
		PageBean<Info> pb = infoDao.findInfosByPage(1, pageSize, "");
		request.setAttribute("pb", pb);
		request.getRequestDispatcher("/admin/info/list.jsp").forward(request, response);
	}

	private void getImg(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
		String img = request.getParameter("imgName");
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream("C:/infopub/" + img);
		} catch (FileNotFoundException e) {
			try {
				fileInputStream = new FileInputStream("C:/infopub/NotFound.jpg");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		try {
			int i;
			i = fileInputStream.available();
			byte[] buff = new byte[i];
			fileInputStream.read(buff);
			fileInputStream.close();
			response.setContentType("image/*");
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(buff);
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * 对新闻进行编辑
	 */
	private void editInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InfoDao infoDao = new InfoDao();
		CategoryDao categoryDao = new CategoryDao();
		/*
		 * 
		 */
		Info info = infoDao.findInfoByInfoId(Integer.parseInt(request.getParameter("infoId")));
		request.setAttribute("info", info);
		/*
		 * 
		 */
		List<Category> categorylist = categoryDao.findAllCategory();
		request.setAttribute("categorylist", categorylist);
		/*
		 * 
		 */
		request.getRequestDispatcher("/admin/info/edit.jsp").forward(request, response);
	}

	private void toAddInfoPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategoryDao categoryDao = new CategoryDao();
		List<Category> list = categoryDao.findAllCategory();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/admin/info/add.jsp").forward(request, response);
	}

	/*
	 * 删除一条新闻
	 */
	private void delInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InfoDao infoDao = new InfoDao();
		Info info = new Info();
		info.setInfoId(Integer.parseInt(request.getParameter("infoId")));
		infoDao.delInfo(info);
		/*
		 * 
		 */
		PageBean<Info> pb = infoDao.findInfosByPage(1, pageSize, "");
		request.setAttribute("pb", pb);
		request.getRequestDispatcher("/admin/info/list.jsp").forward(request, response);
	}

	/*
	 * 分页查询新闻
	 */
	private void findInfosByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InfoDao infoDao = new InfoDao();
		String keywords = request.getParameter("keywords");
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		if (keywords != null) {
			keywords = keywords.trim();
		} else {
			keywords = "";
		}
		PageBean<Info> pb = infoDao.findInfosByPage(currentPage, pageSize, keywords);
		request.setAttribute("pb", pb);
		request.setAttribute("keywords", keywords);
		request.getRequestDispatcher("/admin/info/list.jsp").forward(request, response);
	}

	private void addInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InfoDao infoDao = new InfoDao();
		Info info = new Info();
		String imgName = null;
		// 1、创建一个DiskFileItemFactory工厂
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		// 2、创建一个文件上传解析器
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		// 解决上传文件名的中文乱码
		servletFileUpload.setHeaderEncoding("UTF-8");
		try {
			// 1. 得到 FileItem 的集合 items
			List<FileItem> list_fileItems = servletFileUpload.parseRequest(request);
			// 2. 遍历 items:
			for (FileItem fileItem : list_fileItems) {
				// 若是一个一般的表单域, 打印信息
				if (fileItem.isFormField()) {
					switch (fileItem.getFieldName()) {
					case "title": {
						info.setTitle(fileItem.getString("utf-8"));
						break;
					}
					case "contentTitle": {
						info.setContentTitle(fileItem.getString("utf-8"));
						break;
					}
					case "cid": {
						info.setCid(Integer.parseInt(fileItem.getString("utf-8")));
						break;
					}
					case "author": {
						info.setAuthor(fileItem.getString("utf-8"));
						break;
					}
					case "contentAbstract": {
						info.setContentAbstract(fileItem.getString("utf-8"));
						break;
					}
					case "content": {
						info.setContent(fileItem.getString("utf-8"));
						break;
					}
					case "paiXu": {
						info.setPaiXu(Integer.parseInt(fileItem.getString("utf-8")));
						break;
					}
					case "publishStatus": {
						info.setPublishStatus(fileItem.getString("utf-8"));
						break;
					}
					}
					// System.out.println(fileItem.getFieldName() + ": " +
					// fileItem.getString("utf-8"));
				} // if
					// 若是文件域
				else {
					String fileName = fileItem.getName();
					long sizeInBytes = fileItem.getSize();
					if (sizeInBytes != 0) {
						String[] fileNameArray = fileName.split("\\.");
						imgName = UUIDUtils.getUUId() + "." + fileNameArray[1];
						InputStream inputStream = fileItem.getInputStream();
						byte[] buffer = new byte[1024];
						int lenth = 0;
						String filePath = "C:\\infopub\\" + imgName;// 文件最终上传的位置
						info.setPicPath(imgName);
						OutputStream outputStream = new FileOutputStream(filePath);
						while ((lenth = inputStream.read(buffer)) != -1) {
							outputStream.write(buffer, 0, lenth);
						}
						outputStream.close();
						inputStream.close();
					} else {
						System.out.println("img");
					}
				} // else
			} // for
		} catch (Exception e) {
			e.printStackTrace();
		}
		info.setPublishTime(Time.getDateSecond());
		infoDao.addInfo(info);
		/*
		 * 
		 */
		PageBean<Info> pb = infoDao.findInfosByPage(1, pageSize, "");
		request.setAttribute("pb", pb);
		request.getRequestDispatcher("/admin/info/list.jsp").forward(request, response);
	}
	/*
	 * 
	 */

}
