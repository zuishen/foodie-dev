package work.jimmmy.foodie.pojo.bo.center;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@ApiModel(value = "用户对象BO", description = "从客户端，由对象传如的数据封装在此entity中")
public class CenterUserBo {
    @ApiModelProperty(value = "用户名", name = "username", example = "imooc", required = true)
    private String username;

    @ApiModelProperty(value = "密码", name = "password", example = "123456", required = true)
    private String password;

    @ApiModelProperty(value = "确认密码", name = "confirmPassword", example = "123456", required = false)
    private String confirmPassword;

    @ApiModelProperty(value = "用户昵称", name = "nickname", example = "孙", required = false)
    private String nickname;

    @ApiModelProperty(value = "真实姓名", name = "realname", example = "孙", required = false)
    private String realname;

    @ApiModelProperty(value = "手机号", name = "mobile", example = "13799999999", required = false)
    private String mobile;

    @ApiModelProperty(value = "邮箱地址", name = "email", example = "1234556@qq.com", required = false)
    private String email;

    @ApiModelProperty(value = "性别", name = "sex", example = "0：女，1：男，2：保密", required = false)
    private Integer sex;

    @ApiModelProperty(value = "生日", name = "birthday", example = "1970-01-01", required = false)
    private Date birthday;
}
