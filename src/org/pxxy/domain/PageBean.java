package org.pxxy.domain;

import java.util.List;

public class PageBean<T> {

	private int currentPage; // 当前页码
	private int pageSize; // 每页显示条数
	private int count; // 商品数量
	private int totalPage; // 总页数
	private List<T> list; // 当前页的信息集合数据

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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "PageBean [currentPage=" + currentPage + ", pageSize=" + pageSize + ", count=" + count + ", totalPage="
				+ totalPage + ", list=" + list + "]";
	}

}
