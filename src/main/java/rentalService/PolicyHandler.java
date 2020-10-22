package rentalService;

import org.springframework.beans.factory.annotation.Autowired;
import rentalService.config.kafka.KafkaProcessor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }
    @Autowired
    ProductRepository productRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverMyproductDeleted_Myproduct(@Payload MyproductDeleted myproductDeleted){

        if(myproductDeleted.isMe()){
            Product product = new Product();
            product.setName(myproductDeleted.getName());
            product.setQty(myproductDeleted.getQty());
            product.setStatus("DELETED");

            productRepository.save(product);
        }
    }

}
