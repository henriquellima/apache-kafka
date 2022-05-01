package com.henriquellima.apachekafka.consumer;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class Execute {
    public static void main(String[] args) throws IOException {

        log.info("Start Executing");
        EventConsumer eventConsumer = new EventConsumer();
        eventConsumer.execute();

    }
}
