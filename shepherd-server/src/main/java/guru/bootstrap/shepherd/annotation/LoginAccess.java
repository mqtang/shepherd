package guru.bootstrap.shepherd.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tangcheng
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginAccess {

    boolean strict() default true;

    boolean csrf() default true;

}
// 2020/9/16 20:42
