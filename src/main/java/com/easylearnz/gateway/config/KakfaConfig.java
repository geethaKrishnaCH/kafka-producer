package com.easylearnz.gateway.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KakfaConfig {

    public NewTopic createTransactionTopic() {
        return TopicBuilder.name("transaction-topic")
                .partitions(2)
                .replicas(1)
                .build();
    }
}
