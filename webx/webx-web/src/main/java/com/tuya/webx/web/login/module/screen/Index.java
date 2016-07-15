package com.tuya.webx.web.login.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by wangzhongxing on 16/5/26.
 */
public class Index {

    public void execute(Context context, HttpSession session, Navigator nav, HttpServletResponse response) {
        System.out.println("I am index");
    }

}
