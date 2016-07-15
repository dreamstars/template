package com.tuya.webx.web.base.common;

import com.alibaba.citrus.service.requestcontext.parser.ParameterParser;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.util.TurbineUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by wangzhongxing on 16/5/27.
 */
public abstract class BaseScreen extends BaseAction {

    protected static Logger logger = LoggerFactory.getLogger(BaseScreen.class);

    protected abstract ScreenResult frontExecute(ParameterParser paramParser, Context context, HttpSession session, Navigator nav, HttpServletResponse response)
            throws Exception;

    public ScreenResult execute(Context context, HttpSession session, Navigator nav, HttpServletResponse response) throws Exception {
        TurbineRunData rundata = TurbineUtil.getTurbineRunData(getRequest());
        if (rundata == null) {
            logger.error("这里做异常处理");
            return null;
        }

        // 获取页面参数封装对象
        ParameterParser paramParser = rundata.getParameters();
        // 加载默认的context
        setDefaultContext(context);

        //判断是否登录
        boolean isLogin = checkLogin(paramParser, context, session, nav, response);
        if (isLogin){
            //用户已登录,在这里做处理

            ScreenResult result = new ScreenResult();
            try {
                // 调用各个模块的action
                result = frontExecute(paramParser, context, session, nav, response);
                return result;
            }catch (Exception e){
                //异常处理
                e.printStackTrace();
            }
        }else{
            nav.redirectTo("登录界面的URL");
        }

        return null;
    }

    public Boolean checkLogin(ParameterParser paramParser, Context context, HttpSession session, Navigator nav,
                                HttpServletResponse response) throws IOException {

        return null;
    }

}
