package guru.bootstrap.shepherd.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.data.redis.config.ConfigureRedisAction;

/**
 * @author tangcheng
 */
@Configuration
@MapperScan(basePackages = "guru.bootstrap.shepherd.mapper")
@ImportResource(locations = {"classpath:spring-mvc.xml",
        "classpath:spring-component.xml"
})
public class CommonConfiguration {

    /**
     * redis notify-keyspace-events
     */
    @Bean
    public ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;
    }

    @Bean(name = "springSessionDefaultRedisSerializer")
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        return new Jackson2JsonRedisSerializer<>(Object.class);
    }

}
// 2020/9/6 10:40
