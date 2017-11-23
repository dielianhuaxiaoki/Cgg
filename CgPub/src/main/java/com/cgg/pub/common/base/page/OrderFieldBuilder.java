package com.cgg.pub.common.base.page;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Description: OrderFieldBuilder.java
 * 
 */
public class OrderFieldBuilder {
	private List<String> orderByIterms = new ArrayList<String>(5);

	public String build() {
		if (orderByIterms.size() > 0) {
			StringBuilder sb = new StringBuilder("order by");
			for (String each : orderByIterms) {
				sb.append(" ").append(each).append(",");
			}
			return sb.substring(0, sb.length() - 1).toString();
		}
		return "";
	}

	public OrderFieldBuilder addAscOrder(String order) {
		orderByIterms.add(Order.asc(order));
		return this;
	}

	public OrderFieldBuilder addDescOrder(String order) {
		orderByIterms.add(Order.desc(order));
		return this;
	}

	public void reset() {
		orderByIterms.clear();
	}
}