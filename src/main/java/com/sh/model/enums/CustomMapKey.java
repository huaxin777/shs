package com.sh.model.enums;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: CustomKey
 * @Description:
 * @Version: 1.0.0
 * @Date: 2024/3/5 14:53
 * @Author: SH
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomMapKey {
    String value();
}
