package fit.iuh.week_5.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProductOrder {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @ManyToOne
  private Product product;
  private String customerName;
  private String customerEmail;
  private long quantity_order;
}
