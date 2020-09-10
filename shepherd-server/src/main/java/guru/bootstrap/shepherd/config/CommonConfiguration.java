package guru.bootstrap.shepherd.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author tangcheng
 */
@Configuration
@MapperScan(basePackages = "guru.bootstrap.shepherd.mapper")
@ImportResource(locations = {"classpath:spring-mvc.xml",
        "classpath:spring-component.xml"
})
public class CommonConfiguration {
    
}
// 2020/9/6 10:40
