package com.cgg.pub.common.interceptor;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.ValueStack;

public class PagingInterceptor extends AbstractInterceptor {

	private static final String MSG_FIELD = "msg";
	private static final String SUCCESS_FIELD = "success";
	private static final Log LOG = LogFactory.getLog(PagingInterceptor.class);
	private String errorMsg;

	public String intercept(ActionInvocation invocation) throws Exception {
		ValueStack stack = invocation.getStack();
		// initializing the target action
		initAction(stack);

		Class<?> actionClazz = invocation.getAction().getClass();
		Class<?> pagingClazz = Pagination.class;
		// find the instance name in target action of Pagination
		String pagingName = findPagingName(pagingClazz, actionClazz);
		// populate the new instance of pagination to current action
		populatePaginationToAction(pagingName, pagingClazz, actionClazz, stack);
		return invocation.invoke();
	}

	private String findPagingName(Class<?> type, Class<?> fromClazz) {
		PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(fromClazz);
		for (PropertyDescriptor pd : pds) {
			if (pd.getPropertyType().equals(type))
				return pd.getName();
		}
		this.errorMsg = "No pagination defined in " + fromClazz
				+ " or you can remove this intercetor from action configuration!";
		LOG.error(errorMsg);
		throw new RuntimeException(errorMsg);
	}

	private void populatePaginationToAction(String fieldName, Class<?> pagingClazz, Class<?> actionClazz,
			ValueStack stack) throws Exception {
		String currentPageNum = ServletActionContext.getRequest().getParameter("pager.offset");
		// get default constructor which will set both start and limit to -1.
		if (null == currentPageNum) {
			Constructor<Pagination> constructor = null;
			Pagination pagination = null;
			Class<?>[] constructorParams;
			Object[] params;
			constructorParams = new Class<?>[] { Integer.class, Integer.class };
			params = new Object[] { 0, 15 };
			constructor = this.getConstructorByParams(constructorParams);
			pagination = this.newPagination(constructor, params);
			stack.setValue(fieldName, pagination);
		}
	}

	private Pagination newPagination(Constructor<Pagination> constructor, Object[] params) throws Exception {
		return constructor.newInstance(params);
	}

	private Constructor<Pagination> getConstructorByParams(Class<?>[] constructorParams) throws Exception {
		return Pagination.class.getConstructor(constructorParams);
	}

	private void initAction(ValueStack stack) {
		stack.setValue(SUCCESS_FIELD, true);
		stack.setValue(MSG_FIELD, null);
	}
}
