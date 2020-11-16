package com.stream.stream_provider;

import com.stream.stream_provider.Message.MessageUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** 
* MessageUtil Tester. 
* 
* @author <Authors name> 
* @since <pre>11�� 15, 2020</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MessageUtilTest { 

    @Autowired
    private MessageUtil messageUtil;

    @Test
    public void testOutput() throws Exception {
//        messageUtil.output();
        for (int i=0;i<10;i++){
            messageUtil.cusOutput(i+"");
        }
    }


    @Test
    public void partition() throws Exception {
        for (int i=0;i<5;i++){
            messageUtil.partition("25487");
        }
    }

} 
