package fit.iuh.week_5.consumer.publishConsumer;

import fit.iuh.week_5.models.Product;
import fit.iuh.week_5.models.ProductOrder;
import fit.iuh.week_5.services.EmailService;
import fit.iuh.week_5.services.ProductOrderService;
import fit.iuh.week_5.services.ProductService;
import fit.iuh.week_5.utils.BashService;
import fit.iuh.week_5.utils.Convert;
import fit.iuh.week_5.utils.Template;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ReceiveQueue {
  @Autowired
  private ProductOrderService productOrderService;
  @Autowired
  private ProductService productService;
  @Autowired
  private EmailService emailService;

  @JmsListener(destination = "order")
  public void receiveMessage(final Message jsonMessage) throws JMSException {
    if (jsonMessage instanceof TextMessage textMessage) {
      String jsonListOfProduct = textMessage.getText();
      processMessage(jsonListOfProduct);
    }
  }

  private void processMessage(String jsonListOfProduct) {
    BashService cd = new BashService();
    jsonListOfProduct = cd.decode(jsonListOfProduct);

    Convert<ProductOrder> cv = new Convert<>();
    ProductOrder decodeProductOrder = cv.convertJsonToObj(jsonListOfProduct, ProductOrder.class);
    if (decodeProductOrder != null) {
      handleProductOrder(decodeProductOrder);
    } else {
      System.out.println("Không thể giải mã tin nhắn.");
    }
  }

  private void handleProductOrder(ProductOrder decodeProductOrder) {
    Product product = productService.getProductById(decodeProductOrder.getProduct().getId());
    if (product != null && product.getQuantity() > 0
            && product.getQuantity() >= decodeProductOrder.getQuantity_order()) {
      saveOrderAndUpdateProduct(decodeProductOrder, product);
      sendEmailToCustomer(decodeProductOrder, product);
    } else {
      System.out.println("Sản phẩm không có sẵn hoặc không đủ số lượng để đặt hàng.");
    }
  }

  private void saveOrderAndUpdateProduct(ProductOrder decodeProductOrder, Product product) {
    productOrderService.saveProductOrder(decodeProductOrder);
    long quantityBeDecrease = product.getQuantity() - decodeProductOrder.getQuantity_order();
    product.setQuantity(quantityBeDecrease);
    productService.saveProduct(product);
    System.out.println("Đã thêm vào cơ sở dữ liệu và trừ đi" + quantityBeDecrease + " đơn vị số lượng sản phẩm");
  }

  private void sendEmailToCustomer(ProductOrder decodeProductOrder, Product product) {
    String customerName = decodeProductOrder.getCustomerName();
    String customerEmail = decodeProductOrder.getCustomerEmail();
    String subject = "Cảm ơn bạn đã mua hàng của chúng tôi!";
    String body = Template.sendSuccess(customerName, customerEmail, product.getName(),
            decodeProductOrder.getQuantity_order());
    String emailKh = decodeProductOrder.getCustomerEmail();
    emailService.sendEmail(emailKh, body, subject);
  }

}
