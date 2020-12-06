package itmass;

import itmass.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @Autowired
    PaymentRepository paymentRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrdered_PayComplete(@Payload Ordered ordered){

        if(ordered.isMe()){
            // To-Do : SMS 발송, CJ Logistics 연계, ...
            Payment payment = new Payment();
            payment.setOrderId(ordered.getId());
            payment.setPayStatus("payment completed");

            paymentRepository.save(payment);



//            Optional<Payment> paymentOptional = paymentRepository.findById(ordered.getId());
//            Payment payment = paymentOptional.get();
//            payment.setPayStatus(ordered.getStatus());
//            paymentRepository.save(payment);
        }

    }


//    @StreamListener(KafkaProcessor.INPUT)
//    public void wheneverOrdered_PayComplete(@Payload Ordered ordered){
//
//        if(ordered.isMe()){
//            System.out.println("##### listener PayComplete : " + ordered.toJson());
//        }
//    }

}
