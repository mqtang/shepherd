package guru.bootstrap.shepherd.controller;

import guru.bootstrap.encrypt.EncryptComponent;
import guru.bootstrap.shepherd.curator.CuratorConnection;
import guru.bootstrap.shepherd.mapper.CoreUserMapper;
import guru.bootstrap.shepherd.po.CoreUserPO;
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

    private final CoreUserMapper coreUserMapper;

    private final EncryptComponent encryptComponent;

    public IndexController(CoreUserMapper coreUserMapper,
                           UserService userService, EncryptComponent encryptComponent) {
        this.coreUserMapper = coreUserMapper;
        this.userService = userService;
        this.encryptComponent = encryptComponent;
    }

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Object testHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CoreUserPO coreUserPO = new CoreUserPO();
        coreUserPO.setNickname("黑色");
        coreUserPO.setRegisterIp("103.206.188.43");
        coreUserMapper.insertUserCore(coreUserPO);
        return encryptComponent.encode(coreUserPO.getUserId());
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
