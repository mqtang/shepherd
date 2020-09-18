package guru.bootstrap.shepherd.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tangcheng
 */
@Controller
@RequestMapping("/e/error")
public class SpringServletErrorController extends AbstractErrorController {

    @Value("${server.error.path}")
    private String errorPath;

    public SpringServletErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @ResponseBody
    @RequestMapping
    public Object handle(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return "Something unexpected happens";
    }

    @Override
    public String getErrorPath() {
        return errorPath;
    }
}
// 2020/9/18 8:54
