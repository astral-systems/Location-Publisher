package location.publisher.kafka;


import location.publisher.jwt.JWTHelper;
import location.publisher.jwt.JWTHelperProcessor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Properties;
@Component
public class LocationKafkaPublisherProcessor {


    private JWTHelper jwtHelper;


    public LocationKafkaPublisherProcessor() {
        jwtHelper = new JWTHelperProcessor();
    }

    @Scheduled(fixedRate = 1000)
    void publishMessages() {
        var kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "kafka:9092");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        System.out.println("Hiiii");
        var producer = new KafkaProducer<String, String>(kafkaProps);
        var record =
                new ProducerRecord<>("Location", "",
                        jwtHelper.createJwtToken(new HashMap<>()));
        try {
            producer.send(record);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
