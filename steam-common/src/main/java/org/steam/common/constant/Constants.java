package org.steam.common.constant;

/**
 * 公共常量配置类
 *

 */
public class Constants {

    /**
     * 缓存需要的分隔符
     */
    public static final String ELIMITER = ":";

    /**
     * 登录当前用户
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

    /**
     * token有效期（小时）
     */
    public static final int TOKEN_EXPIRES_HOUR = 72;

    /**
     * 存放Authorization的header字段
     */
    public static final String AUTHORIZATION = "Authorization";

    /**
     * 成功响应
     */
    public static final String RESPONSE_SUCCESS = "success";

    // --------- JWT ---------------

    /**
     * The prefix of token
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * JWT - 加密密匙
     */
    public static final String JWT_SECRET = "walker";

    /**
     * token 保存时间
     */
    public static final int JWT_TTL = 3 * 24 * 60 * 60 * 1000;  //millisecond => 72 h

    /**
     * 刷新间隔
     */
    public static final int JWT_REFRESH_INTERVAL = 55 * 60 * 1000;  //millisecond

    /**
     * 刷新时间
     */
    public static final int JWT_REFRESH_TTL = 12 * 60 * 60 * 1000;  //millisecond => 12 h


    // ----------------七牛--------------------
    public static final String QINIU_ACCESS_KEY = "4x0PS30j9MP-q5bog1VKnDXZ0aMaxLA8l4PIV9ai";

    public static final String QINIU_SECRET_KEY = "T3JX0edq22jn9Yqr2yttjQtg41rZtpLTaOAV55WW";

    public static final String QINIU_UPLOAD_TOKEN = "imgforaa";

    public static final String QINIU_UPLOAD_URL = "http://psj2r6emd.bkt.clouddn.com";
}
