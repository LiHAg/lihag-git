package com.golden.monitor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
/**
 * WebSocket为浏览器和服务器之间提供了双工异步通信功能
 * 目前主流浏览器对WebSocket的支持比较好，但实际开发中使用WebSocket工作量会略大
 * 而且增加了浏览器的兼容问题
 * 我们更多的是使用WebSocket的一个子协议stomp，来快速实现功能
 * @author jinma.zheng
 *
 */
@Configuration
@EnableWebSocketMessageBroker//开启使用STOMP协议来传输基于代理的消息，Broker代理的意思
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    /**
     * 配置消息代理，由于我们是实现推送功能，这里的消息代理是/topic
     */
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    /**
     * 注册STOMP协议的节点，并制定映射的URL
     * 和echarts.ajax.js对上：
     * var socket = new SockJS('/echarts-websocket');
     */
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册STOMP协议节点，同时指定使用SockJS协议
        //registry.addEndpoint("/echarts-websocket").withSockJS();
        registry.addEndpoint("/echarts-websocket").withSockJS();
    }

}
