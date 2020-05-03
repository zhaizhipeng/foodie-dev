package com.ysdrzp.service.center;

import com.ysdrzp.bo.center.CenterUserBO;
import com.ysdrzp.pojo.Users;

/**
 * 用户中心-个人信息
 */
public interface ICenterUserService {

    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    Users queryUserInfo(String userId);

    /**
     * 修改用户信息
     * @param userId
     * @param centerUserBO
     */
    Users updateUserInfo(String userId, CenterUserBO centerUserBO);

    /**
     * 用户头像更新
     * @param userId
     * @param faceUrl
     * @return
     */
    Users updateUserFace(String userId, String faceUrl);
}
