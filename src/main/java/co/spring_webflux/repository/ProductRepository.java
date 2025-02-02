package co.spring_webflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import co.spring_webflux.entity.ProductEntity;

public interface ProductRepository extends ReactiveMongoRepository<ProductEntity, String>{
    
}
