package guru.bootstrap.shepherd.filter;

import guru.bootstrap.cookie.DoCookie;
import guru.bootstrap.encrypt.EncryptComponent;
import guru.bootstrap.shepherd.auth.BaseCommand;
import guru.bootstrap.shepherd.util.AppConstant;
import guru.bootstrap.shepherd.util.WebRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author tangcheng
 */
@Component
public class CookieCommandFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(CookieCommandFilter.class);

    private final EncryptComponent encryptComponent;
    private final StringRedisTemplate redisTemplate;

    public CookieCommandFilter(EncryptComponent encryptComponent, StringRedisTemplate redisTemplate) {
        this.encryptComponent = encryptComponent;
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        DoCookie doCookie = new DoCookie(request, response);
        BaseCommand baseCommand = new BaseCommand(request, response);
        buildBaseCommandInfo(doCookie, baseCommand);
        MDC.put("_user", baseCommand.getUserId() != null ? baseCommand.getUserId() + "" : null);
        request.setAttribute(AppConstant.BASE_COMMAND_ATTR, baseCommand);
        doCookie.addCookie(AppConstant.COOKIE_LAST_VISIT_TIME, encryptComponent.encode(System.currentTimeMillis()), AppConstant.ONE_YEAR_SECONDS);
        filterChain.doFilter(request, response);
    }

    private void buildBaseCommandInfo(DoCookie doCookie, BaseCommand baseCommand) {
        String lgn = doCookie.getCookieRawValue(AppConstant.COOKIE_USER_ID);
        Long userId = encryptComponent.decode(lgn);
        baseCommand.setUserId(userId);
        String lvt = doCookie.getCookieRawValue(AppConstant.COOKIE_LAST_VISIT_TIME);
        Long lvTime = encryptComponent.decode(lvt);
        String token = AppConstant.REDIS_LOGIN_STATUS_TOKEN_PREFIX + userId;
        String loginStatusToken = redisTemplate.boundValueOps(token).get();
        baseCommand.setLoginStatusToken(loginStatusToken);
        baseCommand.set_csrf_token(doCookie.getCookieRawValue(AppConstant.COOKIE_CSRF));
        baseCommand.set_lvt(lvTime != null ? new Date(lvTime) : new Date());
        baseCommand.set_version(WebRequestContext.apiVersion());
        baseCommand.set_lan(WebRequestContext.language());
    }

}
// 2020/9/17 17:11
