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
        List<UserAddress> addressList = this.queryAll(addressBo.getAddressId());
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
}
