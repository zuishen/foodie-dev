package work.jimmmy.foodie.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import work.jimmmy.foodie.pojo.UserAddress;
import work.jimmmy.foodie.pojo.bo.AddressBo;
import work.jimmmy.foodie.service.AddressService;
import work.jimmy.foodie.common.utils.JsonResultResponse;
import work.jimmy.foodie.common.utils.MobileEmailUtils;

import java.util.List;

/**
 * 用户在确认订单页面，可以针对收货地址做如下操作：
 * 1.查询用户的所有收货地址列表
 * 2.新增收货地址
 * 3.删除收货地址
 * 4.修改收货地址
 * 5.设置默认地址
 */
@Api(value = "地址相关", tags = {"地址相关的api接口"})
@RestController
@RequestMapping("address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "根据用户id查询收货地址列表", notes = "根据用户id查询收货地址列表")
    @PostMapping("/list")
    public JsonResultResponse list(
            @ApiParam(name = "userId", value = "用户Id", required = true)
            @RequestParam String userId) {
        if (StringUtils.isBlank(userId)) {
            return JsonResultResponse.errorMsg("");
        }
        List<UserAddress> list = addressService.queryAll(userId);
        return JsonResultResponse.ok(list);
    }

    @ApiOperation(value = "用户新增地址", notes = "用户新增地址", httpMethod = "POST")
    @PostMapping("/add")
    public JsonResultResponse add(@RequestBody AddressBo addressBo) {
        JsonResultResponse jsonResultResponse = checkAddress(addressBo);
        if (!jsonResultResponse.isOK()) {
            return jsonResultResponse;
        }
        addressService.addNewUserAddress(addressBo);
        return JsonResultResponse.ok();
    }

    private JsonResultResponse checkAddress(AddressBo addressBo) {
        String receiver = addressBo.getReceiver();
        if (StringUtils.isBlank(receiver)) {
            return JsonResultResponse.errorMsg("收货人不能为空");
        }
        if (receiver.length() > 12) {
            return JsonResultResponse.errorMsg("收货人姓名不能太长");
        }
        String mobile = addressBo.getMobile();
        if (StringUtils.isBlank(mobile)) {
            return JsonResultResponse.errorMsg("收货人手机号不能为空");
        }
        if (mobile.length() != 11) {
            return JsonResultResponse.errorMsg("收货人手机号长度不正确");
        }
        if (!MobileEmailUtils.checkMobileIsOk(mobile)) {
            return JsonResultResponse.errorMsg("收货人手机号格式不正确");
        }

        String province = addressBo.getProvince();
        String city = addressBo.getCity();
        String district = addressBo.getDistrict();
        String detail = addressBo.getDetail();
        if (StringUtils.isBlank(province)
                || StringUtils.isBlank(city)
                || StringUtils.isBlank(district)
                || StringUtils.isBlank(detail)) {
            return JsonResultResponse.errorMsg("收货地址信息不能为空");
        }
        return JsonResultResponse.ok();
    }
}
