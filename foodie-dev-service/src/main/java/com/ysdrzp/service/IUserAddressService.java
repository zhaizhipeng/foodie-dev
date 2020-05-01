package com.ysdrzp.service;

import com.ysdrzp.bo.UserAddressBO;
import com.ysdrzp.pojo.UserAddress;

import java.util.List;

public interface IUserAddressService {

    /**
     * 获取用户地址列表
     * @param userId
     * @return
     */
    List<UserAddress> queryAddressList(String userId);

    /**
     * 新增收货地址
     * @param userAddressBO
     * @return
     */
    UserAddress addUserAddress(UserAddressBO userAddressBO);

    /**
     * 修改收货地址
     * @param userAddressBO
     * @return
     */
    UserAddress updateUserAddress(UserAddressBO userAddressBO);

    /**
     * 删除收货地址
     * @param userId
     * @param addressId
     */
    void deleteUserAddress(String userId, String addressId);

    /**
     * 设为默认收货地址
     * @param userId
     * @param addressId
     */
    void setDefault(String userId, String addressId);
}
