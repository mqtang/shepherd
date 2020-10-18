package guru.bootstrap.shepherd.controller;

import guru.bootstrap.cookie.DoCookie;
import guru.bootstrap.shepherd.po.CoreUserPO;
import guru.bootstrap.shepherd.service.UserService;
import guru.bootstrap.shepherd.service.exception.UserException;
import guru.bootstrap.shepherd.service.user.UserServiceDTO;
import guru.bootstrap.shepherd.util.AppConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tangcheng
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object regHandler(UserCommandDTO userCommandDTO, HttpServletRequest request, HttpServletResponse response)
            throws UserException {
        UserServiceDTO userServiceDTO = userCommandDTO.buildRegUserServiceDTO(request);
        CoreUserPO userPO = userService.register(userServiceDTO);
        DoCookie cookie = new DoCookie(request, response);
        cookie.addCookie(AppConstant.COOKIE_USER_ID, encryptComponent.encode(userPO.getUserId()), AppConstant.ONE_DAY_SECONDS);
        if (logger.isInfoEnabled()) {
            logger.info("visitor [{}] has registered, user id is {}.", userServiceDTO.getUsername(), userPO.getUserId());
        }
        return userPO.getMemberId();
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object loginHandler(UserCommandDTO userCommandDTO, HttpServletRequest request, HttpServletResponse response)
            throws UserException {
        UserServiceDTO userServiceDTO = userCommandDTO.buildLoginUserServiceDTO(request);
        userServiceDTO = userService.login(userServiceDTO);
        DoCookie cookie = new DoCookie(request, response);
        cookie.addCookie(AppConstant.COOKIE_USER_ID, encryptComponent.encode(userServiceDTO.getUserId()),
                AppConstant.ONE_DAY_SECONDS);
        return userServiceDTO.getUsername();
    }

}
// 2020/9/16 10:54
