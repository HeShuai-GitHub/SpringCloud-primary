package com.import_.autoconfig;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.function.Predicate;

/**
 * @program: springCloud
 * @description: 实现ImportSelector接口，自动装载UserConfig类，将它当做配置文件
 * @author: hs
 * @create: 2020-10-13 22:59
 **/

public class UserImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{UserConfig.class.getName()};
    }
}
