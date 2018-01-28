package com.mastery.mshop.domain;

import java.util.List;

public class PageUtil {
	private Integer total;
	private List<Goods> rows;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<Goods> getRows() {
		return rows;
	}
	public void setRows(List<Goods> rows) {
		this.rows = rows;
	}
}
