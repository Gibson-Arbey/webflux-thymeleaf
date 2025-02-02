package co.spring_webflux.service;

import co.spring_webflux.entity.ProductEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {
    
    Flux<ProductEntity> findAll();

    Mono<ProductEntity> findById(String id);

    Mono<ProductEntity> save(ProductEntity productEntity);

    Mono<Void> delete(ProductEntity productEntity);
}
