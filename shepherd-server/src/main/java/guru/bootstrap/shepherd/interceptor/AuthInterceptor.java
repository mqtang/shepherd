package guru.bootstrap.shepherd.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.bootstrap.shepherd.annotation.LoginValidator;
import guru.bootstrap.shepherd.auth.BaseCommand;
import guru.bootstrap.shepherd.http.HttpRestEntity;
import guru.bootstrap.shepherd.http.ResultStatus;
import guru.bootstrap.shepherd.http.ResultStatusEnum;
import guru.bootstrap.shepherd.util.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tangcheng
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HandlerMethod handlerMethod;
        if (handler instanceof HandlerMethod) {
            handlerMethod = (HandlerMethod) handler;
        } else {
            return true;
        }
        BaseCommand command = SpringContextUtil.getBaseCommand();
        LoginValidator loginValidator = handlerMethod.getMethod().getAnnotation(LoginValidator.class);
        if (loginValidator != null && !command.isLogin()) {
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().write(objectMapper.writeValueAsString(buildHttpResponse()));
            response.getWriter().flush();
            return false;
        }
        return true;
    }

    private HttpRestEntity<?> buildHttpResponse() {
        return HttpRestEntity
                .newResult("")
                .withStatus(ResultStatus.newStatus(ResultStatusEnum.NEED_LOGIN));
    }
}
// 2020/9/8 22:19
