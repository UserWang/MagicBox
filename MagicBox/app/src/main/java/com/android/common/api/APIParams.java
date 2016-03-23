package com.android.common.api;

import com.android.common.utils.Constants;
import com.android.common.utils.TimeUtils;

/**
 * Created by wangjundong on 2015/12/8.
 */
public class APIParams {

    public static final String GIRLS_BASE_URL = "http://route.showapi.com/197-1?";
    public static final String JOKE_BASE_URL = "http://api.1-blog.com/biz/bizserver/xiaohua/list.do?";
    public static final String NEWS_BASE_URL = "http://api.1-blog.com/biz/bizserver/news/list.do?";
    public static final String WEATHER_BASE_URL = " http://apis.haoservice.com/weather?";

    public static final String APPID = "showapi_appid";
    public static final String SIGN = "showapi_sign";
    public static final String TIMES_TAMP = "showapi_timestamp";
    public static final String SIGN_METHOD = "showapi_sign_method";
    public static final String RES_GZIP = "showapi_res_gzip";

    public static final String GIRLS_URL = GIRLS_BASE_URL + APPID + "=" + Constants.APPID + "&" + SIGN + "=" + Constants.SIGN + "&" + TIMES_TAMP + "=" + TimeUtils.formatMillisencond2(System.currentTimeMillis()) + "&";

}
