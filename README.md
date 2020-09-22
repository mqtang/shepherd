# Shepherd -- A Zookeeper Helper

[Creating a Multi Module Project](https://spring.io/guides/gs/multi-module)

不在DDL中声明`not null`,除了主键id和枚举字段;
1. 逻辑都在代码中, 遇到需求变化时, 不需要修改表定义;
2. 会导致代码量增加一些;
3. mybatis实际执行的sql会更长;

应用级别的code为数字;
业务级别的为`FP0123`,格式为:`{业务缩写}{编号}`

mvn process used in springboot
[howto-properties-and-configuration](
https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto-properties-and-configuration)
[maven-resource-filtering](
https://stackoverflow.com/questions/36501017/maven-resource-filtering-not-working-because-of-spring-boot-dependency)
[How do I filter resource files?](
https://maven.apache.org/guides/getting-started/index.html#how-do-i-filter-resource-files)

> mvn package -Pproduction -Dmysql=119.45.12.226
> java -jar shepherd-server.jar -Dspring.profiles.active=production

**1.properties**

在SpringBoot中的application.properties文件中，访问pom的profile变量 `@..@` 而不是 `${..}`。

[SpringBoot HowTo](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto-properties-and-configuration)

**2.shell**

```shell
mvn package -Pproduction -Dmysql=123.32.431.9 -DoutputDir=/mpmp
```

**3.pom.xml**

```xml
<!--
profile 中的properties可以在命令行中进行覆盖, 命令行中的参数值优先级更高
-->
<profiles>
        <profile>
            <id>dev</id>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
            <properties>
                <mysql>127.0.0.1</mysql>
            </properties>
        </profile>
        <profile>
            <id>production</id>
            <properties>
                <outputDir>/app</outputDir>
                <mysql>119.45.12.226</mysql>
            </properties>
            <build>
                <filters>
                </filters>
                <resources>
                    <resource>
                        <directory>src/main/resources</directory>
                        <filtering>true</filtering>
                    </resource>
                </resources>
                <plugins>
                    <plugin>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-maven-plugin</artifactId>
                        <configuration>
                            <outputDirectory>${outputDir}</outputDirectory>
                            <finalName>shepherd-server</finalName>
                        </configuration>
                    </plugin>
                </plugins>
            </build>
        </profile>
    </profiles>
```

### **Spring  Session**

[Redis Operation Reference](https://docs.spring.io/spring-session/docs/2.2.3.RELEASE/reference/html5/#api-redisindexedsessionrepository)

> Config class 
> org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration
>

### 关于 ConfigureRedisAction.NO_OP
https://docs.spring.io/spring-session/docs/2.2.3.RELEASE/reference/html5/#api-redisindexedsessionrepository-sessiondestroyedevent



## Spring Session

**1.Spring Session** 

因为redis key的失效策略 [Redis Expires](https://redis.io/commands/expire#appendix-redis-expires), 所以Spring session在redis中新建了一个key, 使用定时任务来主动去将session数据触发redis的删除。

**2.Redis Pub/Sub**

spring session 也使用了 redis的订阅策略, 当session失效时, 应用程序会获得一个事件, 可用来清理资源等等.

```shell
HMSET spring:session:sessions:33fdd1b6-b496-4b33-9f7d-df96679d32fe \
    creationTime 1404360000000 \
	maxInactiveInterval 1800 \
	lastAccessedTime 1404360000000 \
	sessionAttr:name "tang" \
	sessionAttr:age 25
EXPIRE spring:session:sessions:33fdd1b6-b496-4b33-9f7d-df96679d32fe 2100
APPEND spring:session:sessions:expires:33fdd1b6-b496-4b33-9f7d-df96679d32fe ""
EXPIRE spring:session:sessions:expires:33fdd1b6-b496-4b33-9f7d-df96679d32fe 1800
SADD spring:session:expirations:1439245080000 \
	expires:33fdd1b6-b496-4b33-9f7d-df96679d32fe
EXPIRE spring:session:expirations1439245080000 2100
```

