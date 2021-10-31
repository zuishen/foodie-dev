package work.jimmmy.foodie.service;

import work.jimmmy.foodie.pojo.UserAddress;
import work.jimmmy.foodie.pojo.bo.AddressBo;

import java.util.List;

public interface AddressService {
    /**
     * 根据用户id查询用户的收货地址列表
     *
     * @param userId userId
     * @return list
     */
    List<UserAddress> queryAll(String userId);

    /**
     * 新增用户收货地址
     *
     * @param addressBo addressBo
     */
    void addNewUserAddress(AddressBo addressBo);
}
