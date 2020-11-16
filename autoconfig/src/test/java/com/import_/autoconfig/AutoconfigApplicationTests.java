package com.import_.autoconfig;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@EnableUserBean
class AutoconfigApplicationTests {

    /**
     * 测试springboot自动装载
     */
    @Test
    void contextLoads() {
        AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(AutoconfigApplicationTests.class);
        User user = ac.getBean(User.class);
        System.out.println(user);
    }

}
