package org.pxxy.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/*@ParentPackage("json-default")
@Namespace("/")
@Controller("fileAction")  //创建对象
@Scope("prototype")    //多实例方式创建对象
*/
public class FileAction {
	private File[] file;              //文件  
    private String[] fileFileName;    //文件名   
    private String[] filePath;        //文件路径
    private String downloadFilePath;  //文件下载路径
    private InputStream inputStream; 
    
    /**
     * 文件上传
     * @return
     */
	//@Action(value="fileUploadAction",results={@Result(name="success", type = "json") },params = { "contentType", "text/html" })
 public String fileUpload() {
    	System.out.println("aaaaa");
		String path = ServletActionContext.getServletContext().getRealPath("/attached");
		File file = new File(path); // 判断文件夹是否存在,如果不存在则创建文件夹
		if (!file.exists()) {
			file.mkdir();
		}
		System.out.println("bbbbb");
		try {
			if (this.file != null) {
				System.out.println("cccc");
				File f[] = this.getFile();
				filePath = new String[f.length];
				for (int i = 0; i < f.length; i++) {
					String fileName = java.util.UUID.randomUUID().toString(); // 采用时间+UUID的方式随即命名
					String name = fileName + fileFileName[i].substring(fileFileName[i].lastIndexOf(".")); // 保存在硬盘中的文件名
					System.out.println("ddddd");
					FileInputStream inputStream = new FileInputStream(f[i]);
					FileOutputStream outputStream = new FileOutputStream(path+ "\\" + name);
					byte[] buf = new byte[1024];
					int length = 0;
					while ((length = inputStream.read(buf)) != -1) {
						outputStream.write(buf, 0, length);
					}
					System.out.println("eeeee");
					inputStream.close();
					outputStream.flush();
					//文件保存的完整路径
					// 如：D:\tomcat6\webapps\new_infopub\\attached\a0be14a1-f99e-4239-b54c-b37c3083134a.png
					//filePath[i] = path + "\\" + name;
					filePath[i] = name;
					System.out.println(name);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	/**
	 * @return the file
	 */
	public File[] getFile() {
		return file;
	}
	/**
	 * @param file the file to set
	 */
	public void setFile(File[] file) {
		this.file = file;
	}
	/**
	 * @return the fileFileName
	 */
	public String[] getFileFileName() {
		return fileFileName;
	}
	/**
	 * @param fileFileName the fileFileName to set
	 */
	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}
	
	/**
	 * @return the filePath
	 */
	public String[] getFilePath() {
		return filePath;
	}
	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String[] filePath) {
		this.filePath = filePath;
	}
	/**
	 * @return the inputStream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}
	/**
	 * @param inputStream the inputStream to set
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	/**
	 * @return the downloadFilePath
	 */
	public String getDownloadFilePath() {
		return downloadFilePath;
	}
	/**
	 * @param downloadFilePath the downloadFilePath to set
	 */
	public void setDownloadFilePath(String downloadFilePath) {
		this.downloadFilePath = downloadFilePath;
	}
	
}
