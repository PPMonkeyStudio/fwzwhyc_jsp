<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.io.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.apache.struts2.dispatcher.multipart.*" %>
<%@ page import="org.json.simple.*" %>
<%

/**
 * KindEditor JSP
 * 
 * 本JSP程序是演示程序，建议不要直接在实际项目中使用。
 * 如果您确定直接使用本程序，使用之前请仔细确认相关安全设置。
 * 
 */
//文件保存目录路径
String savePath = pageContext.getServletContext().getRealPath("/") + "attached/";


//文件保存目录URL
String saveUrl  = request.getContextPath() + "/attached/";

//定义允许上传的文件扩展名
HashMap<String, String> extMap = new HashMap<String, String>();
extMap.put("image", "gif,jpg,jpeg,png,bmp");
extMap.put("flash", "swf,flv");
extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
extMap.put("file", "doc,docx,xls,xlsx,ppt,pptx,htm,html,txt,zip,rar,gz,bz2");

//最大文件大小
long maxSize = 10000000;

response.setContentType("text/html; charset=UTF-8");

if(!ServletFileUpload.isMultipartContent(request)){
	out.println(getError("请选择文件。"));
	return;
}
//检查目录
File uploadDir = new File(savePath);
if(!uploadDir.isDirectory()){
	out.println(getError("上传目录不存在。"));
	return;
}
//检查目录写权限
if(!uploadDir.canWrite()){
	out.println(getError("上传目录没有写权限。"));
	return;
}

String dirName = request.getParameter("dir");
if (dirName == null) {
	dirName = "image";
}
if(!extMap.containsKey(dirName)){
	out.println(getError("目录名不正确。"));
	return;
}
//创建文件夹
savePath += dirName + "/";
saveUrl += dirName + "/";
File saveDirFile = new File(savePath);
if (!saveDirFile.exists()) {
	saveDirFile.mkdirs();
}
SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
String ymd = sdf.format(new Date());
savePath += ymd + "/";
saveUrl += ymd + "/";
File dirFile = new File(savePath);
if (!dirFile.exists()) {
	dirFile.mkdirs();
}



MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) request; 
File file = wrapper.getFiles("imgFile")[0]; 
String fileName = wrapper.getFileNames("imgFile")[0];
//检查文件大小
		if(file.length() > maxSize){
			String temStr= "上传文件大小超过限制。";
			out.println(getError(temStr));
			return;
		}
//检查扩展名
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
			String temStr= "上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。";
			out.println(getError(temStr));
			return;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
		try {  
            InputStream in = new FileInputStream(file);  
            File uploadFile = new File(savePath, newFileName);  
            OutputStream outFile = new FileOutputStream(uploadFile);  
            byte[] buffer = new byte[1024 * 1024];  
            int length;  
            while ((length = in.read(buffer)) > 0) {  
            	outFile.write(buffer, 0, length);  
            }  
  
            in.close();  
            outFile.close();  
        } catch (FileNotFoundException ex) {  
            ex.printStackTrace();  
        } catch (IOException ex) {  
            ex.printStackTrace();  
        }  
		
		JSONObject obj = new JSONObject();
		obj.put("error", 0);
		obj.put("url", saveUrl + newFileName);
		out.println(obj.toJSONString());
%>
<%!
private String getError(String message) {
	JSONObject obj = new JSONObject();
	obj.put("error", 1);
	obj.put("message", message);
	return obj.toJSONString();
}
%>