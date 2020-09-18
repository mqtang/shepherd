package guru.bootstrap.shepherd.filter;

import guru.bootstrap.cookie.DoCookie;
import guru.bootstrap.encrypt.EncryptComponent;
import guru.bootstrap.shepherd.auth.BaseCommand;
import guru.bootstrap.shepherd.util.AppConstant;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tangcheng
 */
@Component
public class AuthCommandFilter extends OncePerRequestFilter {

    private final EncryptComponent encryptComponent;

    public AuthCommandFilter(EncryptComponent encryptComponent) {
        this.encryptComponent = encryptComponent;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        DoCookie cookie = new DoCookie(request, response);
        String lgn = cookie.getCookieRawValue(AppConstant.COOKIE_USER_ID);
        MDC.put("_user", lgn);
        Long userId = encryptComponent.decode(lgn);
        BaseCommand command = new BaseCommand();
        command.setUserId(userId);
        request.setAttribute(AppConstant.BASE_COMMAND_ATTR, command);
        filterChain.doFilter(request, response);
    }
}
// 2020/9/17 17:11
