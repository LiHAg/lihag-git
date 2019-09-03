package com.golden.monitor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author jinma.zheng
 * @date 2019年4月10日
 */
//@Configuration
public class RestTemplateConfig {
	RestTemplateConfig() {
		System.out.println("+++++++++++++++++DataScheduledTask+++++++++++++++++++++=");
	}

	@Bean
	public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
		return new RestTemplate(factory);
	}

	@Bean
	public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		// 读取超时ms
		factory.setReadTimeout(5000);
		// 连接超时ms
		factory.setConnectTimeout(15000);
		return factory;
	}
}