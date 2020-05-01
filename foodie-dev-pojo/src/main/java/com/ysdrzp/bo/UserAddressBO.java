package com.ysdrzp.bo;

import lombok.Data;

/**
 * 收货地址
 */
@Data
public class UserAddressBO {

    /**
     * 地址 Id
     */
    private String addressId;

    /**
     * 用户 Id
     */
    private String userId;

    /**
     * 收货人
     */
    private String receiver;

    /**
     * 联系电话
     */
    private String  mobile;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区县
     */
    private String district;

    /**
     * 详细地址
     */
    private String detail;

}
