package guru.bootstrap.shepherd.controller;

import guru.bootstrap.encrypt.EncryptComponent;
import guru.bootstrap.encrypt.PasswordEncryptor;
import guru.bootstrap.shepherd.auth.BaseCommand;
import guru.bootstrap.shepherd.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tangcheng
 */
public abstract class BaseController {

    @Autowired
    protected EncryptComponent encryptComponent;

    @Autowired
    protected PasswordEncryptor passwordEncryptor;

    protected BaseCommand getBaseCommand() {
        return SpringContextUtil.getBaseCommand();
    }

    protected HttpServletRequest currentRequest() {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        return requestAttributes.getRequest();
    }

}
// 2020/9/9 20:54
