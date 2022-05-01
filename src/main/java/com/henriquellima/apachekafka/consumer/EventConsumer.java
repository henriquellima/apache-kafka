package com.henriquellima.apachekafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.henriquellima.apachekafka.AWS.SES.sendEmail;

@Slf4j
public class EventConsumer {

    private final KafkaConsumer<String, String> consumer;

    EventConsumer(){
        consumer = createConsumer();
    }

    private KafkaConsumer<String, String> createConsumer() {
        if(consumer != null){
            return consumer;
        }
        Properties properties = new Properties();

        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "default");

        return new KafkaConsumer<String, String>(properties);
    }

    public void execute() throws IOException {
        List<String> topics = new ArrayList<String>();

        topics.add("EventRegister");

        consumer.subscribe(topics);

        log.info("Starting consumer..");

        boolean run = true;

        while(run){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

            for(ConsumerRecord<String, String> record : records){
                sendEmail(record);
                saveMessage();

                if(record.value().equals("close")){
                    run = false;
                }
            }
        }
    }

    private void saveMessage(){
        log.info("Mensagem salva no banco de dados");
    }
}
