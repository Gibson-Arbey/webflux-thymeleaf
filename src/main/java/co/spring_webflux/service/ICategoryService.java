package co.spring_webflux.service;

import co.spring_webflux.entity.CategoryEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICategoryService {
    
    Flux<CategoryEntity> findAll();

    Mono<CategoryEntity> findById(String id);

    Mono<CategoryEntity> save(CategoryEntity categoryEntity);

    Mono<Void> delete(CategoryEntity categoryEntity);
}
