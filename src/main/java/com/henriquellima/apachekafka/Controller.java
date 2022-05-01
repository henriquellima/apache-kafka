package com.henriquellima.apachekafka;

import com.henriquellima.apachekafka.producer.EventProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/sendemail/{value}")
    public void sendEmail(@PathVariable("value") String value){
        EventProducer eventProducer = new EventProducer();

        eventProducer.execute(value);
    }
}
