package guru.bootstrap.shepherd.controller;

import guru.bootstrap.cookie.DoCookie;
import guru.bootstrap.shepherd.http.HttpRestEntity;
import guru.bootstrap.shepherd.http.ResultStatus;
import guru.bootstrap.shepherd.http.ResultStatusEnum;
import guru.bootstrap.shepherd.po.CoreUserPO;
import guru.bootstrap.shepherd.service.UserService;
import guru.bootstrap.shepherd.service.exception.UserException;
import guru.bootstrap.shepherd.service.exception.UserNotExistException;
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
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object regHandler(UserCommandDTO userCommandDTO, HttpServletRequest request, HttpServletResponse response) {
        UserServiceDTO userServiceDTO = userCommandDTO.buildRegUserServiceDTO(request);
        CoreUserPO userPO;
        try {
            userPO = userService.register(userServiceDTO);
        } catch (UserException e) {
            logger.warn("regHandler ::error", e);
            return HttpRestEntity.newEmptyResult()
                    .withStatus(ResultStatus.newStatus(UserStatusEnum.MEMBER_ID_EXISTS));
        }
        DoCookie cookie = new DoCookie(request, response);
        cookie.addCookie(AppConstant.COOKIE_USER_ID, encryptComponent.encode(userPO.getUserId()), AppConstant.ONE_DAY_SECONDS);
        if (logger.isInfoEnabled()) {
            logger.info("visitor [{}] has registered, user id is {}.", userServiceDTO.getUsername(), userPO.getUserId());
        }
        return HttpRestEntity.newResult(userPO.getMemberId())
                .withStatus(ResultStatus.newStatus(ResultStatusEnum.OK));
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object loginHandler(UserCommandDTO userCommandDTO, HttpServletRequest request, HttpServletResponse response) {
        UserServiceDTO userServiceDTO = userCommandDTO.buildLoginUserServiceDTO(request);
        HttpRestEntity<?> restEntity;
        try {
            userServiceDTO = userService.login(userServiceDTO);
        } catch (UserException e) {
            logger.warn("loginHandler ::error", e);
            if (e instanceof UserNotExistException) {
                return HttpRestEntity.newResult(userCommandDTO.getUsername())
                        .withStatus(ResultStatus.newStatus(UserStatusEnum.MEMBER_NOT_EXISTS));
            }
            return HttpRestEntity.newEmptyResult()
                    .withStatus(ResultStatus.newStatus(ResultStatusEnum.UNKNOWN_ERROR));
        }
        boolean isMatch = passwordEncryptor.matches(userCommandDTO.getPassword(), userServiceDTO.getPassword());
        if (isMatch) {
            DoCookie cookie = new DoCookie(request, response);
            cookie.addCookie(AppConstant.COOKIE_USER_ID, encryptComponent.encode(userServiceDTO.getUserId()),
                    AppConstant.ONE_DAY_SECONDS);
            restEntity = HttpRestEntity.newResult(userServiceDTO.getUsername())
                    .withStatus(ResultStatus.newStatus(ResultStatusEnum.OK));
        } else {
            restEntity = HttpRestEntity.newEmptyResult()
                    .withStatus(ResultStatus.newStatus(ResultStatusEnum.NEED_LOGIN));
        }
        return restEntity;
    }

}
// 2020/9/16 10:54
