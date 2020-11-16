package com.stream.stream_consumer.Message;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

/**
 * @program: springCloud
 * @description: 实现消息中间件工具类
 *  使用stream实现消息中间消费消息，必须定义个channel，stream内置了一个channel，可以直接使用它，也可以自定义一个channel
 *  @EnableBinding: 绑定通道
 *  @StreamListener：添加监听器
 * @create: 2020-11-15 11:31
 **/
@Slf4j
@Component
@EnableBinding({Sink.class,CustomChannel.class})
public class MessageUtil {

    @StreamListener(Sink.INPUT)
    public void input(String message){
        log.info("Stream start");
        System.out.println("Stream 接受的信息："+ message);
        log.info("Stream end");
    }

    @StreamListener(CustomChannel.CUSTOM_INPUT)
    public void custom_input(String message){
        log.info("Stream start");
        System.out.println("Stream 接受的信息："+ message);
        log.info("Stream end");
    }

}
