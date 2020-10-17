package guru.bootstrap.shepherd.controller;

import guru.bootstrap.encrypt.PasswordEncryptor;
import guru.bootstrap.shepherd.service.user.RegisterTypeEnum;
import guru.bootstrap.shepherd.service.user.UserServiceDTO;
import guru.bootstrap.shepherd.util.SpringContextUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author tangcheng
 */
public class UserCommandDTO {
    private String username;
    private String password;
    private String method;

    public UserServiceDTO buildRegUserServiceDTO(HttpServletRequest request) {
        UserServiceDTO userServiceDTO = new UserServiceDTO();
        userServiceDTO.setUsername(this.getUsername());
        String encryptPassword = getEncryptedPassword();
        userServiceDTO.setPassword(encryptPassword);
        userServiceDTO.setAuthIp(request.getRemoteAddr());
        userServiceDTO.setAuthType(this.getRegisterTypeKey());
        return userServiceDTO;
    }

    public UserServiceDTO buildLoginUserServiceDTO(HttpServletRequest request) {
        UserServiceDTO userServiceDTO = new UserServiceDTO();
        userServiceDTO.setUsername(this.getUsername());
        userServiceDTO.setAuthType(this.getRegisterTypeKey());
        userServiceDTO.setLogonIp(request.getRemoteAddr());
        userServiceDTO.setUserAgent(request.getHeader("user-agent"));
        userServiceDTO.setLogonTime(new Date());
        return userServiceDTO;
    }

    public Integer getRegisterTypeKey() {
        Integer key =
                RegisterTypeEnum.getTypeKeyByName(this.method);
        if (key != null) {
            return key;
        }
        return RegisterTypeEnum.MEMBER_ID.getTypeKey();
    }

    private String getEncryptedPassword() {
        PasswordEncryptor encryptor = SpringContextUtil.getBean(PasswordEncryptor.class);
        return encryptor.encode(this.getPassword());
    }

    // -------------------------------- plain setter and getters --------------------------------

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
// 2020/9/16 10:59
