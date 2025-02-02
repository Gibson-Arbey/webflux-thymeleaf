package co.spring_webflux.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "products")
public class ProductEntity {
    
    @Id
    private String id;


    private String name;

    private Double price;

    private Date createAt;

    public ProductEntity(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    
}
