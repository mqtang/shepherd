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

