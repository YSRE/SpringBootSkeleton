package com.shy.springbootskeleton;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.shy.common.properties.CharsetProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.shy")
@Slf4j
public class SpringbootskeletonApplication {

	public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
		ConfigurableApplicationContext context =
		SpringApplication.run(SpringbootskeletonApplication.class, args);
		DefaultMQProducer defaultMQProducer = context.getBean(DefaultMQProducer.class);
		for(int i = 0; i <10; i++){
			Message msg = new Message("Test","Test","key",
					("Hello RocketMQ " + i).getBytes(CharsetProperties.charset)
			);
			SendResult result = defaultMQProducer.send(msg);//此方法抛出了好多异常..
			log.info("SendResult: {}", result);
		}
		DefaultMQPushConsumer consumer = context.getBean(DefaultMQPushConsumer.class);
		//下面应该是要做消费
	}

	/**
	 * 重写的获取
	 * @param application
	 * @return
	 */
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(SpringbootskeletonApplication.class);
	}
}
