package fit.iuh.week_5.services;


import fit.iuh.week_5.models.ProductOrder;
import fit.iuh.week_5.repositories.ProductOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductOrderService {
    @Autowired
    private ProductOrderRepo productOrderRepositories;

    public List<ProductOrder> getAllProductOrder() {
        return (List<ProductOrder>) productOrderRepositories.findAll();
    }

    public void saveProductOrder(ProductOrder productOrder) {
        productOrderRepositories.save(productOrder);
    }
}
