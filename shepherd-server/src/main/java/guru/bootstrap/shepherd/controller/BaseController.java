package guru.bootstrap.shepherd.controller;

import guru.bootstrap.cookie.DoCookie;
import guru.bootstrap.encrypt.EncryptComponent;
import guru.bootstrap.encrypt.PasswordEncryptor;
import guru.bootstrap.shepherd.auth.BaseCommand;
import guru.bootstrap.shepherd.util.WebRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tangcheng
 */
public abstract class BaseController {

    @Autowired
    protected EncryptComponent encryptComponent;

    @Autowired
    protected PasswordEncryptor passwordEncryptor;

    protected BaseCommand getBaseCommand() {
        return WebRequestContext.getBaseCommand();
    }

    protected DoCookie buildDoCookie() {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        HttpServletResponse response = requestAttributes.getResponse();
        return new DoCookie(request, response);
    }

    protected HttpServletRequest currentRequest() {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        return requestAttributes.getRequest();
    }

}
// 2020/9/9 20:54
