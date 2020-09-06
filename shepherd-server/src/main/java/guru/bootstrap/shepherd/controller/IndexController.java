package guru.bootstrap.shepherd.controller;

import com.alibaba.fastjson.JSON;
import guru.bootstrap.shepherd.curator.CuratorConnection;
import guru.bootstrap.shepherd.jpa.UserRepository;
import guru.bootstrap.shepherd.mapper.UserMapper;
import guru.bootstrap.shepherd.po.UserPO;
import org.apache.zookeeper.data.ACL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tangcheng
 */
@Controller
@RequestMapping("/c")
public class IndexController {

    private CuratorConnection curatorConnection;

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    public IndexController(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Object testHandler() throws Exception {
        UserPO userPO = userMapper.selectOneById(1L);
        UserPO userPO1 = userRepository.getOne(1L);
        System.out.println(
                JSON.toJSONString(userPO1, true)
        );
        System.out.println();
        List<ACL> acls = new ArrayList<>();
        return this.curatorConnection.getCurator()
                .getChildren()
                .forPath("/");
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
