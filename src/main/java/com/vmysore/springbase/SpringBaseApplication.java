package com.vmysore.springbase;

import com.rabbitmq.client.AMQP;
import com.vmysore.springbase.test.TestConsumer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@SpringBootApplication
public class SpringBaseApplication {

	@Value("${amqp.queue.name}")
	private String queueName;

	@Value("${amqp.exchange.name}")
	private String exchangeName;

	// Should go into configuration file
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public Queue getQueue() {
		return new Queue(queueName, false);
	}

	@Bean
	public TopicExchange getExchange() {
		return new TopicExchange(exchangeName);
	}

	@Bean
	public Binding binding(Queue queue, TopicExchange topicExchange){
		return BindingBuilder.bind(queue).to(topicExchange).with(queueName);
	}

	@Bean
	public MessageListenerAdapter listenerAdapter(TestConsumer consumer){
		return new MessageListenerAdapter(consumer, "receiveMessage");
	}

	@Bean
	public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter){
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBaseApplication.class, args);
	}

}
