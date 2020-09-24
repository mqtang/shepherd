package guru.bootstrap.shepherd.controller;

import guru.bootstrap.cookie.DoCookie;
import guru.bootstrap.shepherd.http.HttpRestEntity;
import guru.bootstrap.shepherd.http.ResultStatus;
import guru.bootstrap.shepherd.http.ResultStatusEnum;
import guru.bootstrap.shepherd.po.CoreUserPO;
import guru.bootstrap.shepherd.service.UserService;
import guru.bootstrap.shepherd.service.exception.UserException;
import guru.bootstrap.shepherd.service.user.UserServiceDTO;
import guru.bootstrap.shepherd.service.user.UserStatusEnum;
import guru.bootstrap.shepherd.util.AppConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.Date;

/**
 * @author tangcheng
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public Object regHandler(UserCommandDTO userCommandDTO, HttpServletRequest request, HttpServletResponse response) {
        UserServiceDTO userServiceDTO = new UserServiceDTO();
        userServiceDTO.setUsername(userCommandDTO.getUsername());
        String encryptPassword = passwordEncryptor.encode(userCommandDTO.getPassword());
        userServiceDTO.setPassword(encryptPassword);
        userServiceDTO.setAuthIp(request.getRemoteAddr());
        userServiceDTO.setAuthType(userCommandDTO.getRegisterTypeKey());
        CoreUserPO userPO;
        try {
            userPO = userService.register(userServiceDTO);
        } catch (UserException e) {
            logger.warn("regHandler ::error", e);
            return HttpRestEntity
                    .newResult("")
                    .withStatus(ResultStatus.newStatus(UserStatusEnum.MEMBER_ID_EXISTS));
        }
        DoCookie cookie = new DoCookie(request, response);
        cookie.addCookie(AppConstant.COOKIE_USER_ID, encryptComponent.encode(userPO.getUserId()), AppConstant.ONE_DAY_SECONDS);
        if (logger.isInfoEnabled()) {
            logger.info("visitor [{}] has registered, user id is {}.", userServiceDTO.getUsername(), userPO.getUserId());
        }
        return HttpRestEntity
                .newResult(userPO.getMemberId())
                .withStatus(ResultStatus.newStatus(ResultStatusEnum.OK));
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object loginHandler(UserCommandDTO userCommandDTO, HttpServletRequest request, HttpServletResponse response) {
        UserServiceDTO userServiceDTO = new UserServiceDTO();
        userServiceDTO.setUsername(userCommandDTO.getUsername());
        userServiceDTO.setAuthType(userCommandDTO.getRegisterTypeKey());
        userServiceDTO.setLogonIp(request.getRemoteAddr());
        userServiceDTO.setUserAgent(request.getHeader("user-agent"));
        userServiceDTO.setLogonTime(new Date());
        userServiceDTO = userService.login(userServiceDTO);
        boolean isMatch = passwordEncryptor.matches(userCommandDTO.getPassword(), userServiceDTO.getPassword());
        HttpRestEntity<?> restEntity;
        if (isMatch) {
            DoCookie cookie = new DoCookie(request, response);
            cookie.addCookie(AppConstant.COOKIE_USER_ID, encryptComponent.encode(userServiceDTO.getUserId()),
                    AppConstant.ONE_DAY_SECONDS);
            restEntity = HttpRestEntity.newResult(userServiceDTO.getUsername())
                    .withStatus(ResultStatus.newStatus(ResultStatusEnum.OK));
        } else {
            restEntity = HttpRestEntity.newResult("")
                    .withStatus(ResultStatus.newStatus(ResultStatusEnum.NEED_LOGIN));
        }
        return restEntity;
    }

}
// 2020/9/16 10:54
