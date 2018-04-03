package org.pxxy.domain;

import java.util.Date;

public class Info {
	private Integer infoId;// 信息编号
	private String title;// 标题（用于列表页面）
	private Category category;
	private String contentTitle;// 用于信息详细页面
	private String contentAbstract;// 内容摘要
	private String content;// 信息内容
	private String picPath;// 预览图片路径
	private String author;// 作者（来源）
	private Date publishTime;// 发布时间

	private String publishStatus;
	private Integer paiXu;

	public String getContentTitle() {
		return contentTitle;
	}

	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}

	public String getContentAbstract() {
		return contentAbstract;
	}

	public void setContentAbstract(String contentAbstract) {
		this.contentAbstract = contentAbstract;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(String publishStatus) {
		this.publishStatus = publishStatus;
	}

	public Integer getPaiXu() {
		return paiXu;
	}

	public void setPaiXu(Integer paiXu) {
		this.paiXu = paiXu;
	}

	public Integer getInfoId() {
		return infoId;
	}

	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
}
