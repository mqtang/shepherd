package guru.bootstrap.shepherd.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author tangcheng
 */
@Configuration
@MapperScan(basePackages = "guru.bootstrap.shepherd.mapper")
public class ScanRepositoryConfig {

}
// 2020/9/6 10:40
