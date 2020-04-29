package com.ysdrzp.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("用户注册传入参数对象")
@Data
public class UserBo {

    @ApiModelProperty(value = "用户名", name = "username", required = true, example = "ysdrzp")
    private String username;


    @ApiModelProperty(value = "密码", name = "password", required = true, example = "123456")
    private String password;


    @ApiModelProperty(value = "确认密码", name = "confirmPassword", required = true, example = "123456")
    private String confirmPassword;

}
