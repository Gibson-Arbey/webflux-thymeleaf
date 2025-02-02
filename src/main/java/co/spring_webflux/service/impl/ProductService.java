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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Mono<ProductEntity> findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Mono<ProductEntity> save(ProductEntity productEntity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Mono<Void> delete(ProductEntity productEntity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
