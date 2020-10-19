package guru.bootstrap.shepherd.util;

/**
 * @author tangcheng
 */
public abstract class AppConstant {

    private AppConstant() {
    }

    public static final String COOKIE_USER_ID = "_uid";
    public static final String COOKIE_USER_NAME = "_lgn";
    public static final String COOKIE_LAST_VISIT_TIME = "_lvt";

    public static final Integer ONE_DAY_SECONDS = 24 * 60 * 60;
    public static final Integer ONE_YEAR_SECONDS = 365 * 24 * 60 * 60;

    public static final String BASE_COMMAND_ATTR = "_app_baseCommand";

    public static final String LANGUAGE_PARAM_NAME = "_lan";
    public static final String LANGUAGE_PARAM_EN = "en";
    public static final String LANGUAGE_PARAM_CN = "cn";

    public static final String API_VERSION_PARAM_NAME = "_version";
    public static final int DEFAULT_API_VERSION = 0;

    public static final String SPRING_SERVLET_ERROR_ATTR = "spring_servlet_error";

}
// 2020/9/16 20:30
