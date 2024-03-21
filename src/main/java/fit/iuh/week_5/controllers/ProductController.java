package fit.iuh.week_5.controllers;

import fit.iuh.week_5.models.Product;
import fit.iuh.week_5.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {
  @Autowired
  private ProductService productService;

  @GetMapping("/product")
  public String showProductPage(Model model) {
    model.addAttribute("newProduct", new Product());
    List<Product> listProduct = productService.getAllProduct();
    model.addAttribute("listProduct", listProduct);

    return "product";
  }


  @PostMapping("/product/add")
  public String saveProduct(Product product) {
    productService.saveProduct(product);
    return "redirect:/product";
  }
}
