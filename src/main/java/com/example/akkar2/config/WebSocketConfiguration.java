package com.example.akkar2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {//register WebSocket endpoints that the clients can use to connect to the server
        registry.addEndpoint("/chat").setAllowedOrigins("*").withSockJS();//ajouter une endpoint appeller chat qui permet a tous les origins a se connecter si un navigateur ne possede pas webSocket withsockjs add support
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app").enableSimpleBroker("/topic");//This means that any messages sent to an application destination that starts with "/app" will be routed to the application controller.//broadcast messages to the connected clients
    }
}
