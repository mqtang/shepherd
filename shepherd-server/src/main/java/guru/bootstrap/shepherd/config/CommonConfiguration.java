package guru.bootstrap.shepherd.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.session.data.redis.config.ConfigureRedisAction;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

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

    @Bean
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        SerializerFactory serializerFactory
                = objectMapper.getSerializerFactory().withSerializerModifier(new NullSafeBeanSerializerModifier());
        objectMapper.setSerializerFactory(serializerFactory);
        return objectMapper;
    }

    private static class NullSafeBeanSerializerModifier extends BeanSerializerModifier {
        @Override
        public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
            for (BeanPropertyWriter writer : beanProperties) {
                Class<?> clz = writer.getType().getRawClass();
                if (Character.class.isAssignableFrom(clz) || CharSequence.class.isAssignableFrom(clz)) {
                    writer.assignNullSerializer(new NullStringJsonSerializer());
                }
                if (Number.class.isAssignableFrom(clz)) {
                    writer.assignNullSerializer(new NullNumberJsonSerializer());
                }
                if (Boolean.class.equals(clz)) {
                    writer.assignNullSerializer(new NullBooleanJsonSerializer());
                }
                if (clz.isArray() || Collection.class.isAssignableFrom(clz)) {
                    writer.assignNullSerializer(new NullArrayJsonSerializer());
                }
            }
            return beanProperties;
        }
    }

    private static class NullStringJsonSerializer extends JsonSerializer<Object> {
        @Override
        public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (value != null) return;
            gen.writeString("");
        }
    }

    private static class NullNumberJsonSerializer extends JsonSerializer<Object> {
        @Override
        public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (value != null) return;
            gen.writeNumber(0);
        }
    }

    private static class NullBooleanJsonSerializer extends JsonSerializer<Object> {
        @Override
        public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (value != null) return;
            gen.writeBoolean(false);
        }
    }

    private static class NullArrayJsonSerializer extends JsonSerializer<Object> {
        @Override
        public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (value != null) return;
            gen.writeStartArray();
            gen.writeEndArray();
        }
    }

}
// 2020/9/6 10:40
