package guru.bootstrap.shepherd.util;

import org.apache.commons.lang.StringUtils;
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

    public static String language() {
        String lan = httpServletRequest().getParameter(AppConstant.LANGUAGE_PARAM_NAME);
        if (AppConstant.LANGUAGE_PARAM_EN.equalsIgnoreCase(lan)) {
            return AppConstant.LANGUAGE_PARAM_EN;
        }
        return AppConstant.LANGUAGE_PARAM_CN;
    }

    public static int apiVersion() {
        HttpServletRequest request = httpServletRequest();
        String ver = request.getParameter(AppConstant.API_VERSION_PARAM_NAME);
        if (StringUtils.isEmpty(ver)) return AppConstant.DEFAULT_API_VERSION;
        try {
            return Integer.parseInt(ver);
        } catch (Exception ex) {
            ex.printStackTrace();
            return AppConstant.DEFAULT_API_VERSION;
        }
    }

}
// 2020/9/29 17:12
