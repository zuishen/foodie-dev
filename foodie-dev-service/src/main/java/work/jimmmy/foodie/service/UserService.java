package work.jimmmy.foodie.service;

public interface UserService {
    /**
     * 判断用户名是否存在
     */
    boolean queryUsernameExist(String username);
}
