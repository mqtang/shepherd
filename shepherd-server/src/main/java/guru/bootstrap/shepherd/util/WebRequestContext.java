package guru.bootstrap.shepherd.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tangcheng
 */
public abstract class WebRequestContext {

    private WebRequestContext() {
    }

    public static HttpServletRequest httpServletRequest() {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        return requestAttributes.getRequest();
    }

    public static boolean isLanguageEn() {
        return AppConstant.LANGUAGE_PARAM_EN
                .equalsIgnoreCase(httpServletRequest().getParameter(AppConstant.LANGUAGE_PARAM_NAME));
    }

}
// 2020/9/29 17:12
