package com.cgg.pub.common.interceptor;

public class Pagination {
	private Integer pageSize = 15;
	private Integer currentPageNum;
	private Integer totalCount;
	private String orderByField;
	private Boolean esc;

	/**
	 * 记录指针开始点
	 */
	private int startPosition;

	/**
	 * 记录指针结束点
	 */
	private int endPosition;

	private int offset;
	/**
	 * 最大页
	 */
	private int maxPage;

	public Pagination() {

	}

	public Pagination(Integer offset, Integer pageSize) {
		this.currentPageNum = offset / pageSize + 1;
		this.pageSize = pageSize;
		startPosition = (currentPageNum - 1) * pageSize;
		endPosition = currentPageNum * pageSize - 1;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(Integer currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
		int maxPageN = -1;
		this.totalCount = totalCount;
		try {
			maxPageN = (totalCount + pageSize - 1) / pageSize; // 计算出总页数
		} catch (Exception e) {
			maxPageN = 1;
		}
		if (currentPageNum > maxPageN) {
			currentPageNum = maxPageN;
		}

		if (currentPageNum <= 0) {
			currentPageNum = 1;
		}

		this.maxPage = maxPageN;
		startPosition = (currentPageNum - 1) * pageSize;
		endPosition = currentPageNum * pageSize - 1;
	}

	public String getOrderByField() {
		return orderByField;
	}

	public void setOrderByField(String orderByField) {
		this.orderByField = orderByField;
	}

	public Boolean getEsc() {
		return esc;
	}

	public void setEsc(Boolean esc) {
		this.esc = esc;
	}

	public int getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(int startPosition) {
		this.startPosition = startPosition;
	}

	public int getEndPosition() {
		return endPosition;
	}

	public void setEndPosition(int endPosition) {
		this.endPosition = endPosition;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
		this.currentPageNum = offset / pageSize + 1;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
}
