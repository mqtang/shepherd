package guru.bootstrap.shepherd.controller;

import guru.bootstrap.cookie.DoCookie;
import guru.bootstrap.shepherd.po.CoreUserPO;
import guru.bootstrap.shepherd.service.UserService;
import guru.bootstrap.shepherd.service.exception.UserException;
import guru.bootstrap.shepherd.service.user.UserServiceDTO;
import guru.bootstrap.shepherd.util.AppConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author tangcheng
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final StringRedisTemplate redisTemplate;

    public UserController(UserService userService, StringRedisTemplate redisTemplate) {
        this.userService = userService;
        this.redisTemplate = redisTemplate;
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
        HttpSession session = request.getSession();
        String strToken = System.currentTimeMillis() + "";
        session.setAttribute(AppConstant.LOGIN_STATUS_SESSION_ATTR, strToken);
        redisTemplate.boundValueOps(AppConstant.REDIS_LOGIN_STATUS_TOKEN_PREFIX + userServiceDTO.getUserId()).set(strToken);
        return userServiceDTO.getUsername();
    }

}
// 2020/9/16 10:54
