package co.spring_webflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import co.spring_webflux.entity.CategoryEntity;

public interface CategoryRepository extends ReactiveMongoRepository<CategoryEntity, String> {
    
}
