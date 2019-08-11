package com.service;

import com.entity.User;
import com.util.Error;

public interface LoginService {
    /**
     * 用户登录
     *
     * @param userInfo 用户实体
     * @return
     */
    Error login(User userInfo);


    /**
     * 管理员登录
     *
     * @param adminInfo 管理员实体
     * @return
     */
//    Error loginAdmin(Admin adminInfo);

    /**
     * 用户退出
     *
     * @param userInfo 用户实体
     * @return
     */
//    int logout(User userInfo);


    /**
     * 管理员退出
     *
     * @param adminInfo 管理员实体
     * @return
     */
//    int logoutAdmin(Admin adminInfo);
}
