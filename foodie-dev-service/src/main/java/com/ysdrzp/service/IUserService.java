package com.ysdrzp.service;

import com.ysdrzp.bo.UserBo;
import com.ysdrzp.pojo.Users;

/**
 * 用户注册登录接口
 */
public interface IUserService {

    /**
     * 用户名是否存在
     * @param username
     * @return
     */
    boolean queryUserNameIsExist(String username);

    /**
     * 注册用户
     * @param userBo
     * @return
     */
    Users createUser(UserBo userBo) throws Exception;
}
