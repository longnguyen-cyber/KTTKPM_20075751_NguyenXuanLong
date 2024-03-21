package fit.iuh.week_5.repositories;

import fit.iuh.week_5.models.ProductOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderRepo extends CrudRepository<ProductOrder, Long> {
}
