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

    /**
     * 用户修改地址
     *
     * @param addressBo addressBo
     */
    void updateUserAddress(AddressBo addressBo);

    /**
     * 删除根据用户id和收货地址id删除收货地址
     *
     * @param userId userId
     * @param addressId addressId
     */
    void deleteUserAddress(String userId, String addressId);

    /**
     * 将传入的地址设为默认地址
     *
     * @param userId userId
     * @param addressId addressId
     */
    void updateUserAddressToBeDefault(String userId, String addressId);
}
