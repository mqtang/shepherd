package guru.bootstrap.shepherd.config;

import guru.bootstrap.shepherd.filter.CookieCommandFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * @author tangcheng
 */
@Configuration
public class ServletFilterConfiguration {
    /**
     * match all request urls
     */
    private static final String MATCH_ALL_PATH_PATTERN = "/*";

    @Bean
    public FilterRegistrationBean<CookieCommandFilter> registerAuthCommandFilter(CookieCommandFilter cookieCommandFilter) {
        FilterRegistrationBean<CookieCommandFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(cookieCommandFilter);
        registrationBean.setUrlPatterns(Collections.singleton(MATCH_ALL_PATH_PATTERN));
        registrationBean.setName("authCommandFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
// 2020/9/18 11:16
