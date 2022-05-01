package com.henriquellima.apachekafka.producer;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Execute {
    public static void main(String[] args) {

        log.info("Executing the app...");
        EventProducer producer = new EventProducer();
        producer.execute("New Value! ");

    }

}