package co.spring_webflux.service.impl;

import org.springframework.stereotype.Service;

import co.spring_webflux.entity.ProductEntity;
import co.spring_webflux.repository.ProductRepository;
import co.spring_webflux.service.IProductService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    @Override
    public Flux<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Mono<ProductEntity> findById(String id) {
        return productRepository.findById(id);
    }

    @Override
    public Mono<ProductEntity> save(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    @Override
    public Mono<Void> delete(ProductEntity productEntity) {
        return productRepository.delete(productEntity);
    }
    
}
