package fit.iuh.week_5.repositories;

import fit.iuh.week_5.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Long> {
}
