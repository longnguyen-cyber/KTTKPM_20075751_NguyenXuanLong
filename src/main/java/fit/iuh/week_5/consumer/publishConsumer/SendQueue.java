package fit.iuh.week_5.consumer.publishConsumer;

import fit.iuh.week_5.models.ProductOrder;
import fit.iuh.week_5.utils.BashService;
import fit.iuh.week_5.utils.Convert;
import jakarta.jms.JMSException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class SendQueue {
  @Qualifier("jmsTemplate")
  @Autowired
  private JmsTemplate jmsTemplate;

  public void sendMessage(ProductOrder productOrder) throws JMSException {
    Convert<ProductOrder> cv = new Convert<>();
    String jsonProductOrder = cv.convertObjToJSON(productOrder);
    BashService cd = new BashService();
    String encodeProductOrder = cd.encode(jsonProductOrder);
    System.out.println("Encoded message: " + encodeProductOrder);

    jmsTemplate.send("order", session -> session.createTextMessage(encodeProductOrder));
    System.out.println("Sent message: " + encodeProductOrder);

  }

}
