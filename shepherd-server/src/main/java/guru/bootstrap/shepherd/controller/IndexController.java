package guru.bootstrap.shepherd.controller;

import guru.bootstrap.cookie.DoCookie;
import guru.bootstrap.encrypt.EncryptComponent;
import guru.bootstrap.shepherd.curator.CuratorConnection;
import guru.bootstrap.shepherd.jpa.UserRepository;
import guru.bootstrap.shepherd.mapper.UserMapper;
import guru.bootstrap.shepherd.po.UserPO;
import guru.bootstrap.shepherd.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tangcheng
 */
@Controller
@RequestMapping("/c")
public class IndexController extends BaseController {

    private CuratorConnection curatorConnection;

    private final UserService userService;

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    private final EncryptComponent encryptComponent;

    public IndexController(UserMapper userMapper, UserRepository userRepository,
                           UserService userService, EncryptComponent encryptComponent) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.userService = userService;
        this.encryptComponent = encryptComponent;
    }

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Object testHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DoCookie cookie = new DoCookie(request, response);
        System.out.println("qw: => " + cookie.getCookieRawValue("_sa"));
        UserPO userPO = userMapper.selectOneById(1L);
        String eid = request.getParameter("eid");
        cookie.addCookie("_sa", encryptComponent.encode(999999999999999999L), 1000);
        Long s = encryptComponent.decode(eid);
        return s;
//        return encryptComponent.decode(eid);
//        UserPO userPO1 = userRepository.getOne(1L);
//        System.out.println(
//                JSON.toJSONString(userPO1, true)
//        );
//        System.out.println();
//        List<ACL> acls = new ArrayList<>();
//        return this.curatorConnection.getCurator()
//                .getChildren()
//                .forPath("/");
//        return curatorConnection.getCurator().getState();
    }

    @ResponseBody
    @RequestMapping(value = "/close", method = RequestMethod.GET)
    public Object closeHandler() {
        this.curatorConnection.getCurator().close();
        return Integer.MIN_VALUE;
    }

    @PostConstruct
    public void init() {
        this.curatorConnection = new CuratorConnection("119.45.12.226", "2181");
    }

}
// 2020/9/3 22:37
