package guru.bootstrap.shepherd.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.bootstrap.shepherd.http.HttpRestEntity;
import guru.bootstrap.shepherd.http.ResultStatus;
import guru.bootstrap.shepherd.http.ResultStatusEnum;
import guru.bootstrap.shepherd.util.AppConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tangcheng
 */
@ControllerAdvice
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {

    private static final Logger logger = LoggerFactory.getLogger(ResponseControllerAdvice.class);

    private final ObjectMapper objectMapper;

    public ResponseControllerAdvice(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return !returnType.getParameterType().equals(HttpRestEntity.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
        response.getHeaders().setContentType(MediaType.asMediaType(MediaType.APPLICATION_JSON_UTF8));
//        HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();
        Object springMvcError = servletRequest.getAttribute(AppConstant.SPRING_SERVLET_ERROR_ATTR);
        HttpRestEntity<?> restEntity;
        Exception exception = null;
        // 避免handler返回值为string的情况, 统一改为json
        if (returnType.getParameterType().equals(String.class)) {
            try {
                if (springMvcError != null) {
                    restEntity = HttpRestEntity.newResult("").withStatus(ResultStatus.newStatus(ResultStatusEnum.UNKNOWN_ERROR));
                } else {
                    restEntity = HttpRestEntity.newResult(body).withStatus(ResultStatus.newStatus(ResultStatusEnum.OK));
                }
                return objectMapper.writeValueAsString(restEntity);
            } catch (JsonProcessingException e) {
                logger.warn("beforeBodyWrite ::error", e);
                exception = e;
            }
        }
        if (springMvcError != null || exception != null) {
            return HttpRestEntity.newResult("").withStatus(ResultStatus.newStatus(ResultStatusEnum.UNKNOWN_ERROR));
        }
        return HttpRestEntity.newResult(body).withStatus(ResultStatus.newStatus(ResultStatusEnum.OK));
    }

}
// 2020/10/18 2:19
