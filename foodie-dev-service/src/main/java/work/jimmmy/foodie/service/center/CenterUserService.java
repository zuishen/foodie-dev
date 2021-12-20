package work.jimmmy.foodie.service.center;

import work.jimmmy.foodie.pojo.Users;
import work.jimmmy.foodie.pojo.bo.center.CenterUserBo;

public interface CenterUserService {

    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    Users queryUserInfo(String userId);

    Users updateUserInfo(String userId, CenterUserBo centerUserBo);

    Users updateUserFace(String userId, String faceUrl);
}
