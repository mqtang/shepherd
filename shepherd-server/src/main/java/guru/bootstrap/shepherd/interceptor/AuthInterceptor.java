package guru.bootstrap.shepherd.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.bootstrap.shepherd.annotation.LoginAccess;
import guru.bootstrap.shepherd.auth.BaseCommand;
import guru.bootstrap.shepherd.http.HttpRestEntity;
import guru.bootstrap.shepherd.http.ResultStatus;
import guru.bootstrap.shepherd.http.ResultStatusEnum;
import guru.bootstrap.shepherd.util.AppConstant;
import guru.bootstrap.shepherd.util.WebRequestContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        LoginAccess loginAccess = handlerMethod.getMethod().getAnnotation(LoginAccess.class);
        if (loginAccess == null) {
            return true;
        }
        BaseCommand command = WebRequestContext.getBaseCommand();
        if (passAccessCheck(command, loginAccess)) {
            return true;
        }
        writeResponse(buildHttpResponse(), response);
        return false;
    }

    private boolean passAccessCheck(BaseCommand command, LoginAccess loginAccess) {
        boolean isLogin = command.isLogin();
        boolean validStatus = validateLoginStatusToken(command);
        checkCsrfHeader(command);
        return isLogin && validStatus;
    }

    private boolean checkCsrfHeader(BaseCommand command) {
        String csrfHeader = command.getRequest().getHeader(AppConstant.X_CSRF_TOKEN_HEADER);
        String csrfCookie = command.get_csrf_token();

        return false;
    }

    private boolean validateLoginStatusToken(BaseCommand command) {
        String token = command.getLoginStatusToken();
        // 已登录, 但缓存中无登录状态
        if (StringUtils.isEmpty(token)) return false;
        HttpSession session = command.getRequest().getSession();
        String statusToken = (String) session.getAttribute(AppConstant.LOGIN_STATUS_SESSION_ATTR);
        return token.equalsIgnoreCase(statusToken);
    }

    private HttpRestEntity<?> buildHttpResponse() {
        return HttpRestEntity.newResult("")
                .withStatus(ResultStatus.newStatus(ResultStatusEnum.NEED_LOGIN));
    }

    private void writeResponse(Object data, HttpServletResponse response) throws Exception {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(data));
        response.getWriter().flush();
    }
}
// 2020/9/8 22:19
