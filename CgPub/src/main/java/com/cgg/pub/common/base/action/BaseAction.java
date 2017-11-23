package com.cgg.pub.common.base.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cgg.pub.common.util.DateJsonValueProcessor;
import com.cgg.pub.common.util.JsonHelper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

//@ParentPackage("base")
//@Controller("baseAction")
//@Scope("prototype")
//@Namespace(value = "")
public class BaseAction extends ActionSupport {
	Log log = LogFactory.getLog("system");
	
	private String hostname;
	protected String returnCode;

	// **/
	// 获取Attribute
	public Object getAttribute(String name) {
		return ServletActionContext.getRequest().getAttribute(name);
	}

	// 设置Attribute
	public void setAttribute(String name, Object value) {
		ServletActionContext.getRequest().setAttribute(name, value);
	}

	public String getHostname() {
		if (hostname == null) {
			hostname = "http://" + getRequest().getServerName();
		}
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	// 获取Parameter
	public String getParameter(String name) {
		return getRequest().getParameter(name);
	}

	// 获取Parameter数组
	public String[] getParameterValues(String name) {
		return getRequest().getParameterValues(name);
	}

	// 获取Session
	public Object getSession(String name) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		return session.get(name);
	}

	// 获取Session
	public Map<String, Object> getSession() {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		return session;
	}

	// 设置Session
	public void setSession(String name, Object value) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		session.put(name, value);
	}

	// 获取Request
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	// 获取Response
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	// 获取Application
	public ServletContext getApplication() {
		return ServletActionContext.getServletContext();
	}

	// AJAX输出，返回null
	public String ajax(String content, String type) {
		try {
			log.debug("content:" + content);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType(type + ";charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// AJAX输出，返回null
	public String ajaxJsonP(String content) {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			String callback = request.getParameter("callback");
			String returnResult = "";
			if (callback != null && !"".equals(callback)) {
				returnResult = callback + "(" + content + ")";
			}

			response.setContentType("text/javascript;charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(returnResult);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// AJAX输出文本，返回null
	public String ajaxText(String text) {
		return ajax(text, "text/plain");
	}

	// AJAX输出HTML，返回null
	public String ajaxHtml(String html) {
		return ajax(html, "text/html");
	}

	// AJAX输出XML，返回null
	public String ajaxXml(String xml) {
		return ajax(xml, "text/xml");
	}

	// 根据字符串输出JSON，返回null
	public String ajaxJson(String jsonString) {
		return ajax(jsonString, "text/html");
	}

	// 根据字符串输出JSON，返回null
	public String ajaxJson(Object jsonobj) {
		return ajax(JsonHelper.objectToJsonByJackson(jsonobj), "text/html");
	}

	// 根据字符串输出JSON，返回null
	public String ajaxJsonAr(String jsonString) {
		return ajax("[" + jsonString + "]", "text/html");
	}

	// obj 转化成 json
	protected String getJsonDataAsString(Object obj) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		JSONObject array = JSONObject.fromObject(obj, jsonConfig);
		return array.toString(); // 返回的数据
	}

	// obj 转化成 json并输出
	protected String objAjaxJson(Object obj) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		JSONObject array = JSONObject.fromObject(obj, jsonConfig);
		return ajaxJson(array.toString()); // 返回的数据
	}

	// JSON 转化成 map
	public Map<String, String> jsonStringToMap(String jsonString) {
		JSONObject jasonObject = JSONObject.fromObject(jsonString);
		Map<String, String> map = (Map<String, String>) jasonObject;
		return map;
	}

	public Map<String, Object> jsonStringToMap2(String jsonString) {
		JSONObject jasonObject = JSONObject.fromObject(jsonString);
		Map<String, Object> map = (Map<String, Object>) jasonObject;
		return map;
	}

	public Map<Object, Object> jsonStringToMap3(String jsonString) {
		JSONObject jasonObject = JSONObject.fromObject(jsonString);
		Map<Object, Object> map = (Map<Object, Object>) jasonObject;
		return map;
	}

	// 根据Map输出JSON，返回null
	public String ajaxJson(Map<String, String> jsonMap) {
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		return ajax(jsonObject.toString(), "text/html");
	}

	// 输出JSON警告消息，返回null
	public String ajaxJsonWarnMessage(String message) {
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put(STATUS, WARN);
		jsonMap.put(MESSAGE, message);
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		return ajax(jsonObject.toString(), "text/html");
	}

	// 输出JSON成功消息，返回null
	public String ajaxJsonSuccessMessage(String message) {
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put(STATUS, SUCCESS);
		jsonMap.put(MESSAGE, message);
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		return ajax(jsonObject.toString(), "text/html");

	}

	// 输出JSON错误消息，返回null
	public String ajaxJsonErrorMessage(String message) {
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put(STATUS, ERROR);
		jsonMap.put(MESSAGE, message);
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		return ajax(jsonObject.toString(), "text/html");
	}

	// 设置页面不缓存
	public void setResponseNoCache() {
		if (getRequest().getProtocol().compareTo("HTTP/1.0") == 0)
			getResponse().setHeader("Pragma", "no-cache");
		if (getRequest().getProtocol().compareTo("HTTP/1.1") == 0)
			getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().setHeader("progma", "no-cache");
		getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().setHeader("Cache-Control", "no-store");
		getResponse().setDateHeader("Expires", 0);
	}

	protected static final String STATUS = "status";
	protected static final String WARN = "warn";
	protected static final String SUCCESS = "success";
	protected static final String ERROR = "error";
	protected static final String FAIL = "fail";
	protected static final String MESSAGE = "message";
	protected static final String FREEMARKER = "freemarker";
	protected static final String REDIRECT = "redirect";
	protected static final String LOGIN = "login";

	private static final long serialVersionUID = 1L;

	/* START - set & get function */
	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getStaticResourceDomain() {
		return this.getRequest().getContextPath();
	}

	public String getContextPath() {
		return getRequest().getContextPath();
	}

	// syslog

	/* END - set & get function */
	
	protected static ObjectMapper mapper = new ObjectMapper();
	protected static JsonFactory factory = mapper.getJsonFactory();

	protected void writeJSON(HttpServletResponse response, String json) {
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void writeJSON(HttpServletResponse response, Object obj) {
		response.setContentType("text/html;charset=utf-8");
		try {
			JsonGenerator responseJsonGenerator = factory.createJsonGenerator(response.getOutputStream(),
					JsonEncoding.UTF8);
			responseJsonGenerator.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}