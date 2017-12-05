package com.qcsj.service.ServiceUtil;

import javax.servlet.http.HttpSession;

/**
 * 通用工具
 *
 * @author Brendan Lee
 */
public class ServiceUtil {
    /**
     * 验证当前会话是否已登录
     *
     * @param session 会话session
     * @return 已登录返回true，未登录返回false
     */
    public static boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("uid") != null;
    }
}
