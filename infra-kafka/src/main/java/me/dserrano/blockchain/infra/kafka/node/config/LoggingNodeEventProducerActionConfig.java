package me.dserrano.blockchain.infra.kafka.node.config;

import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import me.dserrano.blockchain.infra.kafka.node.model.NodeEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.SendResult;

import java.util.function.Consumer;
import java.util.function.Function;

@Configuration
@Slf4j
@Generated
public class LoggingNodeEventProducerActionConfig {
    @Bean
    Consumer<SendResult<String, NodeEvent>> onNodeEventProducerSuccessAction() {
        return sendResult -> {
            log.info("SelfNode notification delivered to " +
                    "topic [" + sendResult.getRecordMetadata().topic() + "] " +
                    "partition [" + sendResult.getRecordMetadata().partition() + "] " +
                    "offset [" + sendResult.getRecordMetadata().offset() + "]");
        };
    }

    @Bean
    Function<Throwable, Void> onNodeEventProducerErrorAction() {
        return error -> {
            log.error("There was an error sending selfNode notification", error);
            return null;
        };
    }
}
