package com.ysdrzp.controller;

import com.ysdrzp.bo.UserBo;
import com.ysdrzp.pojo.Users;
import com.ysdrzp.service.IUserService;
import com.ysdrzp.utils.YSDRZPJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户注册登录模块
 */
@Api(value = "用户注册登录", tags = {"用户注册登录相关接口"})
@RestController
@RequestMapping("passport")
public class PassportController {

    @Autowired
    IUserService userService;

    /**
     * 校验用户名是否存在
     * @param username
     * @return
     */
    @ApiOperation(value = "校验用户名是否存在", notes = "created by @ysdrzp", httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public YSDRZPJSONResult usernameIsExist(@RequestParam String username){

        if (StringUtils.isBlank(username)){
            return YSDRZPJSONResult.errorMsg("用户名不能为空");
        }

        boolean isExist = userService.queryUserNameIsExist(username);
        if (isExist){
            return YSDRZPJSONResult.errorMsg("用户名已经存在");
        }

        return YSDRZPJSONResult.ok();
    }

    /**
     * 用户注册
     * @param userBo
     * @return
     */
    @ApiOperation(value = "用户注册", notes = "created by @ysdrzp", httpMethod = "POST")
    @PostMapping("/register")
    public YSDRZPJSONResult register(@RequestBody UserBo userBo){

        if (StringUtils.isBlank(userBo.getUsername()) || StringUtils.isBlank(userBo.getPassword())
                || StringUtils.isBlank(userBo.getCheckPassword())){
            return YSDRZPJSONResult.errorMsg("用户名或密码不能为空");
        }

        if (! userBo.getPassword().equals(userBo.getCheckPassword())){
            return YSDRZPJSONResult.errorMsg("两次密码输入不一致");
        }

        if (userBo.getPassword().length() < 6){
            return YSDRZPJSONResult.errorMsg("密码长度过短，请设置一个至少6位长度的密码");
        }

        boolean isExist = userService.queryUserNameIsExist(userBo.getUsername());
        if (isExist){
            return YSDRZPJSONResult.errorMsg("用户名已经存在");
        }

        Users users;
        try{
            users = userService.createUser(userBo);
        }catch (Exception e){
            e.printStackTrace();
            return YSDRZPJSONResult.errorException("用户注册异常");
        }

        return YSDRZPJSONResult.ok(users);
    }
}
