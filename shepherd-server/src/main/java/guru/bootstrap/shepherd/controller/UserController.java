package guru.bootstrap.shepherd.controller;

import guru.bootstrap.cookie.DoCookie;
import guru.bootstrap.shepherd.http.HttpRestEntity;
import guru.bootstrap.shepherd.http.ResultStatus;
import guru.bootstrap.shepherd.http.ResultStatusEnum;
import guru.bootstrap.shepherd.po.CoreUserPO;
import guru.bootstrap.shepherd.service.UserService;
import guru.bootstrap.shepherd.service.exception.UserException;
import guru.bootstrap.shepherd.service.user.UserServiceDTO;
import guru.bootstrap.shepherd.util.AppConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public Object regHandler(UserCommandDTO userCommandDTO, HttpServletRequest request, HttpServletResponse response) {

        UserServiceDTO userServiceDTO = new UserServiceDTO();
        userServiceDTO.setUsername(userCommandDTO.getUsername());
        String encryptPassword = passwordEncryptor.encode(userCommandDTO.getPassword());
        userServiceDTO.setPassword(encryptPassword);
        userServiceDTO.setRegisterIp(request.getRemoteAddr());
        userServiceDTO.setRegisterType(userCommandDTO.getRegisterTypeKey());
        CoreUserPO userPO;
        try {
            userPO = userService.register(userServiceDTO);
        } catch (UserException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        DoCookie cookie = new DoCookie(request, response);
        cookie.addCookie(AppConstant.COOKIE_USER_ID, encryptComponent.encode(userPO.getUserId()), AppConstant.ONE_DAY_SECONDS);
        cookie.addCookie(AppConstant.COOKIE_LAST_VISIT_TIME, encryptComponent.encode(System.currentTimeMillis()), AppConstant.ONE_YEAR_SECONDS);
        return HttpRestEntity
                .newResult(userPO.getMemberId())
                .withStatus(ResultStatus.newStatus(ResultStatusEnum.OK));
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object loginHandler(UserCommandDTO userCommandDTO, HttpServletRequest request, HttpServletResponse response) {
        logger.warn("RequestURL: " + request.getRequestURL());
        logger.warn("RequestURI:" + request.getRequestURI());
        logger.warn("RemoteAddr:" + request.getRemoteAddr());
        logger.warn("RemoteHost:" + request.getRemoteHost());
        logger.warn("RemotePort" + request.getRemotePort());
        UserServiceDTO userServiceDTO = new UserServiceDTO();
        userServiceDTO.setUsername(userCommandDTO.getUsername());
        userServiceDTO.setRegisterType(userCommandDTO.getRegisterTypeKey());
        userServiceDTO = userService.login(userServiceDTO);
        boolean isMatch = passwordEncryptor.matches(userCommandDTO.getPassword(), userServiceDTO.getPassword());
        HttpRestEntity<?> restEntity;
        if (isMatch) {
            DoCookie cookie = new DoCookie(request, response);
            cookie.addCookie(AppConstant.COOKIE_USER_ID, encryptComponent.encode(userServiceDTO.getUserId()), AppConstant.ONE_DAY_SECONDS);
            cookie.addCookie(AppConstant.COOKIE_LAST_VISIT_TIME, encryptComponent.encode(System.currentTimeMillis()), AppConstant.ONE_YEAR_SECONDS);
            restEntity = HttpRestEntity.newResult(userServiceDTO.getUsername()).withStatus(ResultStatus.newStatus(ResultStatusEnum.OK));
        } else {
            restEntity = HttpRestEntity.newResult("").withStatus(ResultStatus.newStatus(ResultStatusEnum.NEED_LOGIN));
        }
        return restEntity;
    }

}
// 2020/9/16 10:54
