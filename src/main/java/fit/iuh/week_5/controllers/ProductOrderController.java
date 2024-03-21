package fit.iuh.week_5.controllers;

import fit.iuh.week_5.consumer.publishConsumer.SendQueue;
import fit.iuh.week_5.models.Product;
import fit.iuh.week_5.models.ProductOrder;
import fit.iuh.week_5.services.EmailService;
import fit.iuh.week_5.services.ProductOrderService;
import fit.iuh.week_5.services.ProductService;
import fit.iuh.week_5.utils.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductOrderController {
  @Autowired
  private ProductService productService;
  @Autowired
  private ProductOrderService productOrderService;
  @Autowired
  private SendQueue sendMessage;
  @Autowired
  private EmailService emailService;

  @GetMapping("/order")
  public String ShowProductOrderPage(Model model) {
    model.addAttribute("newOrder", new ProductOrder());
    List<ProductOrder> listProductOrdered = productOrderService.getAllProductOrder();
    List<Product> listProduct = productService.getAllProduct();
    model.addAttribute("listProduct", listProduct);
    model.addAttribute("listProductOrdered", listProductOrdered);
    return "order";
  }

  @PostMapping("/order/add")
  public String saveProduct(@ModelAttribute("newOrder") ProductOrder productOrder,
                            @RequestParam("productId") Long productId) {
    Product product = productService.getProductById(productId);
    productOrder.setProduct(product);

    if (product.getQuantity() > 0 && product.getQuantity() >= productOrder.getQuantity_order()) {
      try {
        sendMessage.sendMessage(productOrder);
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      String customerName = productOrder.getCustomerName();
      String productName = productOrder.getProduct().getName();
      long quantityOrdered = productOrder.getQuantity_order();
      String customerEmail = productOrder.getCustomerEmail();
      String emailNpp = "longnguyendev2020@gmail.com";
      String subject = "Thông báo về đơn hàng của bạn";
      String body = Template.sendReject(customerName, productName, quantityOrdered, customerEmail, emailNpp);
      emailService.sendEmail(customerEmail, body, subject);
      return "redirect:/error";
    }

    return "redirect:/order";
  }

  @GetMapping("/")
  public String Homepage(Model model) {
    System.out.println("Homepage");
    return "redirect:/order";
  }

}
