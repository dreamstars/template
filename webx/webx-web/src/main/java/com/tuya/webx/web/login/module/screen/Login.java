package com.tuya.webx.web.login.module.screen;

import com.alibaba.citrus.service.requestcontext.parser.ParameterParser;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.tuya.webx.dal.dao.impl.TUserDAOImpl;
import com.tuya.webx.dal.entity.TUserDO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by wangzhongxing on 16/5/26.
 */
public class Login {

    @Resource(name="UserDAOImpl")
    TUserDAOImpl userDAOImpl;

    /**
     * execute方法就是专门用来处理对应请求的，是webx潜规则的地方，它是页面渲染之前处理的方法。
     * webx框架在找到对应的module类时会自动执行该类下的execute方法
     *
     * @param context
     * @param session
     */
    public void execute(ParameterParser params, Context context, HttpSession session, Navigator nav, HttpServletResponse response){

        String username = params.getString("username");
        String password = params.getString("password");

        TUserDO user = userDAOImpl.getByUsername(username);
        if (user == null){
            context.put("result", "false");
            context.put("msg", "用户名或密码有误!");
            return;
        }
        if (user.getPassword().equals(password)){
            nav.redirectTo("homeLink");
        }
    }

}
