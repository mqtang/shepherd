package guru.bootstrap.shepherd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.bootstrap.shepherd.http.ResultStatus;
import guru.bootstrap.shepherd.service.exception.ServiceRootException;
import guru.bootstrap.shepherd.util.AppConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tangcheng
 */
@Component
public class GlobalHandlerExceptionResolver implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(GlobalHandlerExceptionResolver.class);

    private final ObjectMapper objectMapper;

    public GlobalHandlerExceptionResolver(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        if (ex instanceof ServiceRootException) {
            ServiceRootException rootException = (ServiceRootException) ex;
            MappingJackson2JsonView jsonView = new MappingJackson2JsonView(objectMapper);
            modelAndView.setView(jsonView);
            modelAndView.addObject("result", "");
            modelAndView.addObject("status",
                    new ResultStatus(rootException.getCodeWithPrefix(),
                            isEnLanguage(request) ? rootException.getMsgEn() : rootException.getMsg()));
            logger.warn("GlobalHandlerExceptionHandler resolveException ::error", ex);
            return modelAndView;
        }
        throw new RuntimeException(ex);
    }

    private boolean isEnLanguage(HttpServletRequest request) {
        String lan = request.getParameter(AppConstant.LANGUAGE_PARAM_NAME);
        return AppConstant.LANGUAGE_PARAM_EN.equalsIgnoreCase(lan);
    }
}
// 2020/10/18 1:22
