package com.henriquellima.apachekafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

@Slf4j
public class EventProducer {

    private final Producer<String, String> producer;

    public EventProducer() {
        this.producer = createProducer();
    }

    private Producer<String, String> createProducer(){

        if(producer != null){
            return producer;
        }

        Properties properties = new Properties();

        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("serializer.class", "kafka.serializer.DefaultEncoder");

        return new KafkaProducer<String, String>(properties);

    };

    public void execute(String value){

        String key = UUID.randomUUID().toString();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSS");


        log.info("Starting to send a message.");
        ProducerRecord<String, String> record = new ProducerRecord<String, String>("EventRegister", key, value);

        producer.send(record);
        producer.flush();
        producer.close();
        log.info("Message has sent succesfully [{}]", value);

    }

}
