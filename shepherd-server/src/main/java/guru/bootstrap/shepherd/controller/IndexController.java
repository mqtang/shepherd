package guru.bootstrap.shepherd.controller;

import guru.bootstrap.shepherd.annotation.LoginAccess;
import guru.bootstrap.shepherd.curator.CuratorConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author tangcheng
 */
@Controller
@RequestMapping("/c")
public class IndexController extends BaseController {

    private CuratorConnection curatorConnection;

    @Autowired
    private Environment environment;

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Object testHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        session.setAttribute("name", "tang_cheng");
        session.setAttribute("age", 25);
        String age =
                environment.getProperty("spring.age", "default");
//        return new SimpleDateFormat("yyyy-MM-dd hh:mm:sss").format(new Date());
        return age;
    }

    @LoginAccess
    @ResponseBody
    @RequestMapping(value = "/close", method = RequestMethod.GET)
    public Object closeHandler(HttpServletRequest request) {
        this.curatorConnection.getCurator().close();
        return Integer.MIN_VALUE;
    }

    @PostConstruct
    public void init() {
        this.curatorConnection = new CuratorConnection("119.45.12.226", "2181");
    }
}
// 2020/9/3 22:37
