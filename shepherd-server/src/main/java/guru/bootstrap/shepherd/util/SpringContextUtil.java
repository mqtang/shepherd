package guru.bootstrap.shepherd.util;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tangcheng
 */
public abstract class SpringContextUtil {
    private SpringContextUtil() {
    }

    public static <T> T getBean(Class<T> clazz) {
        return getWebApplicationContext().getBean(clazz);
    }

    public static WebApplicationContext getWebApplicationContext() {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        return WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
    }
}
// 2020/9/9 14:37
