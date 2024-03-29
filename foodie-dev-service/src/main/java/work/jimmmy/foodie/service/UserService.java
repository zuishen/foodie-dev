package work.jimmmy.foodie.service;

import work.jimmmy.foodie.pojo.Users;
import work.jimmmy.foodie.pojo.bo.UserBo;

public interface UserService {
    /**
     * 判断用户名是否存在
     */
    boolean queryUsernameExist(String username);

    /**
     * 创建用户(前端传到后端用于接收的数据体，都可以定义为BO : business object)
     */
    Users createUser(UserBo userBo);

    /**
     * 检索用户名和密码是否匹配
     *
     * @param username 用户名
     * @param password 密码
     * @return user
     */
    Users queryUserForLogin(String username, String password);
}
