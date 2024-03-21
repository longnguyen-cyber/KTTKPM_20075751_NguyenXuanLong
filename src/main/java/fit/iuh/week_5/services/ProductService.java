package fit.iuh.week_5.services;


import fit.iuh.week_5.models.Product;
import fit.iuh.week_5.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepository;

    public List<Product> getAllProduct() {
        return (List<Product>) productRepository.findAll();
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).get();
    }

}
