package com.import_.autoconfig;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Import 注解引入外部的类
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
@Import(UserImportSelector.class)
public @interface EnableUserBean {
}
