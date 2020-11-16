package com.import_.autoconfig;

import org.springframework.context.annotation.Bean;

/**
 * @program: springCloud
 * @description:
 * @author: hs
 * @create: 2020-10-13 22:55
 **/
public class UserConfig {

    /**
     * 通过实现 ImportSelector 来将bean加载到spring容器中
     * @return
     */
    @Bean
    public User getUser(){
        User user= new User();
        user.setAge(24);
        user.setName("小帅");
        return user;
    }

}
