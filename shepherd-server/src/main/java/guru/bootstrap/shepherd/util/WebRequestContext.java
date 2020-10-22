package guru.bootstrap.shepherd.util;

import guru.bootstrap.shepherd.auth.BaseCommand;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tangcheng
 */
public abstract class WebRequestContext {

    private static final Logger logger = LoggerFactory.getLogger(WebRequestContext.class);

    private WebRequestContext() {
    }

    public static BaseCommand getBaseCommand() {
        return (BaseCommand) currentRequest().getAttribute(AppConstant.BASE_COMMAND_ATTR);
    }

    public static boolean isLanguageEn() {
        return AppConstant.LANGUAGE_PARAM_EN
                .equalsIgnoreCase(currentRequest().getParameter(AppConstant.LANGUAGE_PARAM_NAME));
    }

    public static String language() {
        String lan = currentRequest().getParameter(AppConstant.LANGUAGE_PARAM_NAME);
        if (AppConstant.LANGUAGE_PARAM_EN.equalsIgnoreCase(lan)) {
            return AppConstant.LANGUAGE_PARAM_EN;
        }
        return AppConstant.LANGUAGE_PARAM_CN;
    }

    public static int apiVersion() {
        String ver = currentRequest().getParameter(AppConstant.API_VERSION_PARAM_NAME);
        if (StringUtils.isEmpty(ver)) return AppConstant.DEFAULT_API_VERSION;
        try {
            return Integer.parseInt(ver);
        } catch (Exception ex) {
            logger.warn("apiVersion ::error", ex);
            return AppConstant.DEFAULT_API_VERSION;
        }
    }

    public static HttpServletRequest httpServletRequest() {
        return currentRequest();
    }

    public static HttpServletResponse httpServletResponse() {
        return currentResponse();
    }

    // ------------------------------- private section -------------------------------
    private static HttpServletRequest currentRequest() {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        return requestAttributes.getRequest();
    }

    private static HttpServletResponse currentResponse() {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        return requestAttributes.getResponse();
    }
}
// 2020/9/29 17:12
