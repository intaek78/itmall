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
    DeliveryRepository deliveryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPayCompleted_Ship(@Payload PayCompleted payCompleted){
        if(payCompleted.isMe()){
            //System.out.println("type_complete: "+payCompleted.eventType);
            Delivery delivery = new Delivery();
            delivery.setOrderId(payCompleted.getOrderId());
            delivery.setStatus("(delivery)Delivery Started!!");
            deliveryRepository.save(delivery);
            //System.out.println("##### listener Ship : " + payCompleted.toJson());
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPayCancelled_DeliveryCancel(@Payload PayCancelled payCancelled){

        if(payCancelled.isMe()){
            //System.out.println("type_cancel: "+payCancelled.eventType);
            Optional<Delivery> deliveryOptional = deliveryRepository.findById(payCancelled.getOrderId());
            Delivery delivery = deliveryOptional.get();//위에서 find한 오더 객체를 찾아서 매핑
            delivery.setStatus("(delivery)delivery cancelled!!");
            deliveryRepository.save(delivery);
        }
    }

}
