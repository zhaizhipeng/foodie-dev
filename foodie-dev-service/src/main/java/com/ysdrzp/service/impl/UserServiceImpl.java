package com.ysdrzp.service.impl;

import com.ysdrzp.bo.UserBo;
import com.ysdrzp.enums.Sex;
import com.ysdrzp.mapper.UsersMapper;
import com.ysdrzp.pojo.Users;
import com.ysdrzp.service.IUserService;
import com.ysdrzp.utils.CookieUtils;
import com.ysdrzp.utils.DateUtil;
import com.ysdrzp.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * 用户注册登录接口实现
 */
@Service
public class UserServiceImpl implements IUserService {

    /**
     * 默认头像
     */
    private static final String USER_FACE = "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";

    @Autowired
    UsersMapper usersMapper;

    @Override
    public boolean queryUserNameIsExist(String username) {

        Example example = new Example(Users.class);
        Example.Criteria usersCriteria = example.createCriteria();
        usersCriteria.andEqualTo("username", username);

        Users users = usersMapper.selectOneByExample(example);
        if (users != null){
            return true;
        }
        return false;
    }

    @Override
    public Users createUser(UserBo userBo) throws Exception {

        Users users = new Users();
        users.setId(DateUtil.generateId());
        users.setUsername(userBo.getUsername());
        users.setPassword(MD5Utils.getMD5Str(userBo.getPassword()));
        users.setBirthday(DateUtil.convertToDate("1900-01-01"));
        users.setNickname(userBo.getUsername());
        users.setSex(Sex.sexSerect.getType());
        users.setFace(USER_FACE);
        users.setCreatedTime(DateUtil.getCurrentTimestamp());
        users.setUpdatedTime(DateUtil.getCurrentTimestamp());
        usersMapper.insertSelective(users);

        users = setPropertyNull(users);
        return users;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserForLogin(String username, String password) throws Exception {

        Example example = new Example(Users.class);
        Example.Criteria usersCriteria = example.createCriteria();
        usersCriteria.andEqualTo("username", username);
        usersCriteria.andEqualTo("password", MD5Utils.getMD5Str(password));

        Users resultUsers = usersMapper.selectOneByExample(example);
        resultUsers = setPropertyNull(resultUsers);

        return resultUsers;
    }

    private Users setPropertyNull(Users users){
        users.setPassword(null);
        users.setMobile(null);
        users.setCreatedTime(null);
        users.setUpdatedTime(null);
        users.setBirthday(null);
        return users;
    }

}
