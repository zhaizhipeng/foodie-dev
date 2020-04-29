package com.ysdrzp.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户注册传入参数对象
 */
@ApiModel("用户注册传入参数对象")
public class UserBo {

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", name = "username", required = true, example = "ysdrzp")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "password", required = true, example = "123456")
    private String password;

    /**
     * 确认密码
     */
    @ApiModelProperty(value = "确认密码", name = "checkPassword", required = true, example = "123456")
    private String checkPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCheckPassword() {
        return checkPassword;
    }

    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
    }
}
