package com.cgg.pub.common.base.page;

import java.util.ArrayList;
import java.util.List;

public class GroupByFieldBuilder {
	private List<String> groupByIterms = new ArrayList<String>(5);

	public String build() {
		if (groupByIterms.size() > 0) {
			StringBuilder sb = new StringBuilder("group by");
			for (String each : groupByIterms) {
				sb.append(" ").append(each).append(",");
			}
			return sb.substring(0, sb.length() - 1).toString();
		}
		return "";
	}

	public GroupByFieldBuilder addGroupBy(String filed) {
		groupByIterms.add(filed);
		return this;
	}

	public void reset() {
		groupByIterms.clear();
	}
}
