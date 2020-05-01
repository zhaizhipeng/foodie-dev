package com.ysdrzp.controller;

import com.ysdrzp.bo.UserAddressBO;
import com.ysdrzp.pojo.UserAddress;
import com.ysdrzp.service.IUserAddressService;
import com.ysdrzp.utils.YSDRZPJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "地址 controller", tags = {"收货地址 API"})
@RestController
@RequestMapping("address")
public class UserAddressController {

    @Autowired
    IUserAddressService userAddressService;

    @ApiOperation(value = "获取用户收货地址列表", notes = "created by @ysdrzp", httpMethod = "POST")
    @PostMapping("/list")
    public YSDRZPJSONResult list(@ApiParam(value = "用户Id", name = "userId", required = true) @RequestParam String userId){
        if (StringUtils.isBlank(userId)){
            return YSDRZPJSONResult.errorMsg(null);
        }

        List<UserAddress> list = userAddressService.queryAddressList(userId);
        return YSDRZPJSONResult.ok(list);
    }

    @ApiOperation(value = "新增收货地址", notes = "created by @ysdrzp", httpMethod = "POST")
    @PostMapping("/add")
    public YSDRZPJSONResult add(@RequestBody UserAddressBO userAddressBO){

        if (StringUtils.isBlank(userAddressBO.getUserId())){
            return YSDRZPJSONResult.errorMsg("用户Id为空");
        }

        if (StringUtils.isBlank(userAddressBO.getMobile())){
            return YSDRZPJSONResult.errorMsg("手机号为空");
        }

        if (StringUtils.isBlank(userAddressBO.getReceiver())){
            return YSDRZPJSONResult.errorMsg("收货人为空");
        }

        if (StringUtils.isBlank(userAddressBO.getProvince()) || StringUtils.isBlank(userAddressBO.getCity())
                || StringUtils.isBlank(userAddressBO.getDistrict()) || StringUtils.isBlank(userAddressBO.getDetail())){
            return YSDRZPJSONResult.errorMsg("收货地址为空");
        }

        UserAddress userAddress = userAddressService.addUserAddress(userAddressBO);
        return YSDRZPJSONResult.ok(userAddress);
    }

    @ApiOperation(value = "修改收货地址", notes = "created by @ysdrzp", httpMethod = "POST")
    @PostMapping("/update")
    public YSDRZPJSONResult update(@RequestBody UserAddressBO userAddressBO){

        if (StringUtils.isBlank(userAddressBO.getAddressId())){
            return YSDRZPJSONResult.errorMsg("请选择收货地址");
        }

        UserAddress userAddress = userAddressService.updateUserAddress(userAddressBO);
        return YSDRZPJSONResult.ok(userAddress);
    }

    @ApiOperation(value = "删除收货地址", notes = "created by @ysdrzp", httpMethod = "POST")
    @PostMapping("/delete")
    public YSDRZPJSONResult delete(@ApiParam(value = "用户Id", name = "userId", required = true) @RequestParam String userId,
                                   @ApiParam(value = "地址Id", name = "addressId", required = true) @RequestParam String addressId){

        if (StringUtils.isBlank(userId)){
            return YSDRZPJSONResult.errorMsg(null);
        }

        if (StringUtils.isBlank(addressId)){
            return YSDRZPJSONResult.errorMsg(null);
        }

        userAddressService.deleteUserAddress(userId, addressId);
        return YSDRZPJSONResult.ok();
    }

    @ApiOperation(value = "设为默认地址", notes = "created by @ysdrzp", httpMethod = "POST")
    @PostMapping("/setDefault")
    public YSDRZPJSONResult setDefault(@ApiParam(value = "用户Id", name = "userId", required = true) @RequestParam String userId,
                                   @ApiParam(value = "地址Id", name = "addressId", required = true) @RequestParam String addressId){

        if (StringUtils.isBlank(userId)){
            return YSDRZPJSONResult.errorMsg(null);
        }

        if (StringUtils.isBlank(addressId)){
            return YSDRZPJSONResult.errorMsg(null);
        }

        userAddressService.setDefault(userId, addressId);
        return YSDRZPJSONResult.ok();
    }

}
