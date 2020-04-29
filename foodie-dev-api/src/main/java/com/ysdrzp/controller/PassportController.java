package com.ysdrzp.controller;

import com.ysdrzp.bo.UserBo;
import com.ysdrzp.pojo.Users;
import com.ysdrzp.service.IUserService;
import com.ysdrzp.utils.CookieUtils;
import com.ysdrzp.utils.JsonUtils;
import com.ysdrzp.utils.YSDRZPJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户注册登录模块
 */
@Api(value = "用户注册登录", tags = {"用户注册登录相关接口"}, hidden = true)
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
    public YSDRZPJSONResult usernameIsExist(@RequestParam @ApiParam(name = "username", value = "用户名", defaultValue = "ysdrzp") String username){

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
    @PostMapping("/regist")
    public YSDRZPJSONResult regist(@RequestBody UserBo userBo, HttpServletRequest request, HttpServletResponse response){

        if (StringUtils.isBlank(userBo.getUsername()) || StringUtils.isBlank(userBo.getPassword())
                || StringUtils.isBlank(userBo.getConfirmPassword())){
            return YSDRZPJSONResult.errorMsg("用户名或密码不能为空");
        }

        if (! userBo.getPassword().equals(userBo.getConfirmPassword())){
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

            CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(users), true);
        }catch (Exception e){
            e.printStackTrace();
            return YSDRZPJSONResult.errorException("用户注册异常");
        }

        return YSDRZPJSONResult.ok(users);
    }

    /**
     * 用户登录
     * @param userBo
     * @return
     */
    @ApiOperation(value = "用户登录", notes = "created by @ysdrzp", httpMethod = "POST")
    @PostMapping("/login")
    public YSDRZPJSONResult login(@RequestBody UserBo userBo, HttpServletRequest request, HttpServletResponse response){

        if (StringUtils.isBlank(userBo.getUsername()) || StringUtils.isBlank(userBo.getPassword())){
            return YSDRZPJSONResult.errorMsg("用户名或密码不能为空");
        }

        Users users;
        try{
            users = userService.queryUserForLogin(userBo.getUsername(), userBo.getPassword());
            if (users == null){
                return YSDRZPJSONResult.errorMsg("用户名或密码不正确");
            }

            CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(users), true);

        }catch (Exception e){
            e.printStackTrace();
            return YSDRZPJSONResult.errorException("用户登录异常");
        }

        return YSDRZPJSONResult.ok(users);
    }
}
