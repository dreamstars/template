package com.tuya.webx.web.base.common;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by wangzhongxing on 16/6/13.
 */
public class BaseAction {

    // 返回错误页面
    public static String ERROR = "error";
    // 返回登录页面
    public static String LOGIN = "login";
    // 执行成功
    public static String SUCCESS = "success";

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @Autowired
    protected HttpSession session;

    protected HttpServletRequest getRequest() {
        return request;
    }

    protected HttpServletResponse getResponse() {
        return response;
    }

    protected HttpSession getSession() {
        return session;
    }

    public void setDefaultContext(Context context) {
        // url后面的参数字符串(主要用于搜索)
        context.put("queryString", request.getQueryString());
        // 当前url(主要用于登录跳转)
        context.put("redirectURL", this.getRedirectURL());
    }

    /**
     * 获取当前的URL
     *
     * @return
     */
    protected String getRedirectURL() {
        String redirectURL = request.getRequestURL().toString();
        if (request.getQueryString() != null) {
            redirectURL += "?" + request.getQueryString();
        }
        return redirectURL;
    }

    protected String getSessionId() {
        return session.getId();
    }

    /**
     * 获取用户IP
     */
    protected String getIpAddress() {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    protected String outputResponse(boolean status, String msg, String callback) {
        JSONObject json = new JSONObject();
        json.put("status", status);
        json.put("msg", msg);
        outputResponseJson(json.toString(), callback);
        return SUCCESS;
    }

    /**
     * 异步返回数据
     *
     * @param jsonMap
     * @param callback
     * @throws Exception
     */
    protected void outputResponseJson(String jsonMap, String callback) {
        getResponse().setContentType("text/json; charset=UTF-8");
        PrintWriter out = null;
        try {
            out = getResponse().getWriter();
            if (StringUtils.isEmpty(callback)) {
                out.println("\n" + jsonMap.toString());
            } else {
                out.println("\n" + callback + "(" + jsonMap.toString() + ")");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 记录日志
     * @param log
     */
    public void writeLog(String log){

    }

}
