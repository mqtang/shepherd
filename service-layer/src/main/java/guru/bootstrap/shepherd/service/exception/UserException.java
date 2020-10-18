package guru.bootstrap.shepherd.service.exception;

import guru.bootstrap.shepherd.service.user.UserStatusEnum;

/**
 * @author tangcheng
 */
public class UserException extends ServiceRootException {

    private UserStatusEnum userStatusEnum;

    public UserException(UserStatusEnum userStatusEnum) {
        super(userStatusEnum.getDescEn());
        this.userStatusEnum = userStatusEnum;
    }

    @Override
    public String getCodeWithPrefix() {
        return this.userStatusEnum.getCodeWithPrefix();
    }

    @Override
    public String getMsg() {
        return this.userStatusEnum.getDesc();
    }

    @Override
    public String getMsgEn() {
        return this.userStatusEnum.getDescEn();
    }
}
// 2020/9/16 11:36
