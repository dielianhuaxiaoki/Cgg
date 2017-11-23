package com.cgg.pub.common.base.page;

public class Order {
	public static String asc(String orderBy) {
		return new StringBuilder(" ").append(orderBy).append(" asc ").toString();
	}

	public static String desc(String orderBy) {
		return new StringBuilder(" ").append(orderBy).append(" desc ").toString();
	}
}
