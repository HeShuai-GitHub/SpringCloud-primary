package com.stream.stream_provider.Message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @program: springCloud
 * @description: 自定义消息通道
 * @create: 2020-11-15 12:33
 **/

public interface CustomChannel {
    String CUSTOM_OUTPUT = "custom_output";
    String CUSTOM_INPUT = "custom_input";

    @Output("custom_output")
    MessageChannel cusOutput();

    @Input("custom_input")
    SubscribableChannel cutInput();
}
