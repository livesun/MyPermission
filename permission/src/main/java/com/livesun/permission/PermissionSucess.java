package com.livesun.permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类描述：请求成功的注解
 * 创建人：livesun
 * 创建时间：2017/8/28
 * 修改人：
 * 修改时间：
 * github：https://github.com/livesun
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionSucess {
    int value();
}
