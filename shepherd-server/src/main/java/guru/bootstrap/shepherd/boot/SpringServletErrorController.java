package guru.bootstrap.shepherd.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author tangcheng
 */
@Controller
@RequestMapping("/e/error")
public class SpringServletErrorController extends AbstractErrorController {

    private static final Logger logger = LoggerFactory.getLogger(SpringServletErrorController.class);

    @Value("${server.error.path}")
    private String errorPath;

    public SpringServletErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @ResponseBody
    @RequestMapping
    public Object handle(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = getErrorAttributes(request, true);
        request.setAttribute("spring_servlet_error", map);
        logger.warn(System.lineSeparator() + map.toString());
        return "something unexpected happened, please try again later";
    }

    @Override
    public String getErrorPath() {
        return errorPath;
    }
}
// 2020/9/18 8:54
