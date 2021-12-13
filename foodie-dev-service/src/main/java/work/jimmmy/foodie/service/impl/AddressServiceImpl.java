package work.jimmmy.foodie.service.impl;

import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import work.jimmmy.foodie.mapper.UserAddressMapper;
import work.jimmmy.foodie.pojo.UserAddress;
import work.jimmmy.foodie.pojo.bo.AddressBo;
import work.jimmmy.foodie.service.AddressService;
import work.jimmy.foodie.common.enums.YesOrNo;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<UserAddress> queryAll(String userId) {
        UserAddress ua = new UserAddress();
        ua.setUserId(userId);

        return userAddressMapper.select(ua);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addNewUserAddress(AddressBo addressBo) {
        // 1.判断当前用户是否存在地址，如果没有则新增为默认地址
        List<UserAddress> addressList = this.queryAll(addressBo.getUserId());
        int isDefault = 0;
        if (addressList == null || addressList.isEmpty()) {
            isDefault = 1;
        }
        // 2.保存地址到数据库
        UserAddress newAddress = new UserAddress();
        BeanUtils.copyProperties(addressBo, newAddress); // 属性匹配即可拷贝
        newAddress.setId(sid.nextShort());
        newAddress.setIsDefault(isDefault);
        newAddress.setCreatedTime(new Date());
        newAddress.setUpdatedTime(new Date());
        userAddressMapper.insert(newAddress);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateUserAddress(AddressBo addressBo) {
        String addressId = addressBo.getAddressId();
        UserAddress pendingAddress = new UserAddress();
        BeanUtils.copyProperties(addressBo, pendingAddress);
        pendingAddress.setId(addressId);
        pendingAddress.setUpdatedTime(new Date());
        // 不更新值为null的属性，updateByPrimaryKey
        userAddressMapper.updateByPrimaryKeySelective(pendingAddress);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteUserAddress(String userId, String addressId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        userAddress.setId(addressId);
        userAddressMapper.deleteByPrimaryKey(userAddress);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateUserAddressToBeDefault(String userId, String addressId) {
        // 1.查找默认地址，设置为不默认
        UserAddress queryAddress = new UserAddress();
        queryAddress.setUserId(userId);
        queryAddress.setIsDefault(YesOrNo.YES.type);
        List<UserAddress> list = userAddressMapper.select(queryAddress);
        for (UserAddress userAddress : list) {
            userAddress.setIsDefault(YesOrNo.NO.type);
            userAddressMapper.updateByPrimaryKeySelective(userAddress);
        }
        // 2.根据地址id修改为默认地址
        UserAddress defaultAddress = new UserAddress();
        defaultAddress.setId(addressId);
        defaultAddress.setUserId(userId);
        defaultAddress.setIsDefault(YesOrNo.YES.type);
        userAddressMapper.updateByPrimaryKeySelective(defaultAddress);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public UserAddress queryUserAddress(String userId, String addressId) {
        UserAddress singleAddress = new UserAddress();
        singleAddress.setId(addressId);
        singleAddress.setUserId(userId);

        return userAddressMapper.selectOne(singleAddress);
    }
}
