package com.mastery.mshop.domain;
//分页查询
public class Page {
	private final int index;
	private final int count;
	private final int start;
	private final int end;

	/**
	 * 
	 * @param index
	 * @param count
	 * 100数据
	 * 5：：：10
	 * 
	 * 
	 */
	
	// 提供分页的页数和每页展示的条数
	public Page(int index, int count) {
		this.index = index;
		this.count = count;
		this.start = (index - 1) * count + 1;
		this.end = index * count;
	}

	@Override
	public String toString() {
		return "Page [index=" + index + ", count=" + count + ", start=" + start + ", end=" + end + "]";
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

}
