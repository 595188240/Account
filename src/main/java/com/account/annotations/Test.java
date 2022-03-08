package com.account.annotations;

import java.lang.annotation.*;

/**
 * @author ffdeng2
 * @date 2022-3-4 16:55
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Test {

    String value() default "";

}
