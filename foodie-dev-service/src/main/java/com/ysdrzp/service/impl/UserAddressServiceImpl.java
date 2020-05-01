package com.ysdrzp.service.impl;

import com.ysdrzp.bo.UserAddressBO;
import com.ysdrzp.enums.IsDefault;
import com.ysdrzp.enums.YesOrNo;
import com.ysdrzp.mapper.UserAddressMapper;
import com.ysdrzp.pojo.UserAddress;
import com.ysdrzp.service.IUserAddressService;
import com.ysdrzp.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserAddressServiceImpl implements IUserAddressService {

    @Autowired
    UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddress> queryAddressList(String userId) {
        Example userAddressExample = new Example(UserAddress.class);
        Example.Criteria userAddressCriteria = userAddressExample.createCriteria();
        userAddressCriteria.andEqualTo("userId", userId);

        List<UserAddress> list = userAddressMapper.selectByExample(userAddressExample);
        return list;
    }

    @Override
    public UserAddress addUserAddress(UserAddressBO userAddressBO) {

        UserAddress userAddress = new UserAddress();
        userAddress.setId(DateUtil.generateId());
        userAddress.setUserId(userAddressBO.getUserId());
        userAddress.setMobile(userAddressBO.getMobile());
        userAddress.setReceiver(userAddressBO.getReceiver());
        userAddress.setProvince(userAddressBO.getProvince());
        userAddress.setCity(userAddressBO.getCity());
        userAddress.setDistrict(userAddressBO.getDistrict());
        userAddress.setDetail(userAddressBO.getDetail());
        userAddress.setIsDefault(IsDefault.No.type);
        userAddress.setCreatedTime(DateUtil.getCurrentDateTime());
        userAddress.setUpdatedTime(DateUtil.getCurrentDateTime());

        userAddressMapper.insertSelective(userAddress);
        return userAddress;
    }

    @Override
    public UserAddress updateUserAddress(UserAddressBO userAddressBO) {

        UserAddress userAddress = new UserAddress();
        userAddress.setId(userAddressBO.getAddressId());
        userAddress.setUserId(userAddressBO.getUserId());
        userAddress.setMobile(userAddressBO.getMobile());
        userAddress.setReceiver(userAddressBO.getReceiver());
        userAddress.setProvince(userAddressBO.getProvince());
        userAddress.setCity(userAddressBO.getCity());
        userAddress.setDistrict(userAddressBO.getDistrict());
        userAddress.setDetail(userAddressBO.getDetail());
        userAddress.setCreatedTime(DateUtil.getCurrentDateTime());
        userAddress.setUpdatedTime(DateUtil.getCurrentDateTime());

        userAddressMapper.updateByPrimaryKeySelective(userAddress);
        return userAddress;
    }

    @Override
    public void deleteUserAddress(String userId, String addressId) {

        Example deleteUserAddressExample = new Example(UserAddress.class);
        Example.Criteria deleteUserAddressCriteria = deleteUserAddressExample.createCriteria();
        deleteUserAddressCriteria.andEqualTo("userId", userId);
        deleteUserAddressCriteria.andEqualTo("id", addressId);

        // 删除收货地址
        userAddressMapper.deleteByExample(deleteUserAddressExample);
    }

    @Override
    public void setDefault(String userId, String addressId) {

        UserAddress queryAddress = new UserAddress();
        queryAddress.setUserId(userId);
        queryAddress.setIsDefault(IsDefault.Yes.type);
        List<UserAddress> list  = userAddressMapper.select(queryAddress);
        for (UserAddress ua : list) {
            ua.setIsDefault(YesOrNo.no.type);
            userAddressMapper.updateByPrimaryKeySelective(ua);
        }

        UserAddress userAddress = new UserAddress();
        userAddress.setId(addressId);
        userAddress.setUserId(userId);
        userAddress.setIsDefault(IsDefault.Yes.type);
        userAddressMapper.updateByPrimaryKeySelective(userAddress);
    }

}
