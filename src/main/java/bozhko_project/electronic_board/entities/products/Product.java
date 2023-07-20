package bozhko_project.electronic_board.entities.products;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "product_name")
	private String productName;


	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Category category;

	@Column(name = "description")
	private String description;

	@Column(name = "price")
	private double price;


	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Brand brand;

	@Column(name = "image_id")
	private Long imageId;

}
