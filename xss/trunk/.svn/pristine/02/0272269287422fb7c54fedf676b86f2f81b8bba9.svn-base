package com.xp.brushms.dao.mongo;

import java.util.List;

/**
 * 分页数据类
 * 
 * @author Skyline
 * 
 *         2012-10-26 下午8:23:15
 */
public class Pagination<T> {

	/**
	 * 一页数据默认20条
	 */
	private int pageSize = 20;
	/**
	 * 当前页码
	 */
	private int pageNo;

	/**
	 * 一共有多少条数据
	 */
	private long totalCount;

	/**
	 * 一共有多少页
	 */
	private int totalPage;
	/**
	 * 数据集合
	 */
	private List<T> datas;

	/**
	 * 分页的url
	 */
	private String pageUrl;


	public Pagination(int pageNo, int pageSize, long totalCount2) {
		this.setPageNo(pageNo);
		this.setPageSize(pageSize);
		this.setTotalCount(totalCount2);
		this.setTotalPage();
	}


	/**
	 * 获取第一条记录位置
	 * 
	 * @return
	 */
	public int getFirstResult() {
		return (this.getPageNo() - 1) * this.getPageSize();
	}

	/**
	 * 计算一共多少页
	 */
	public void setTotalPage() {
		this.totalPage = (int) ((this.totalCount % this.pageSize > 0) ? (this.totalCount / this.pageSize + 1)
				: this.totalCount / this.pageSize);
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount2) {
		this.totalCount = totalCount2;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public int getNumberOfElements() {
		if (datas == null) return 0;
		return datas.size();
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public boolean isHasNext() {
		return pageNo < totalPage;
	}

	public boolean isHasPrevious() {
		return pageNo > 1;
	}

}
