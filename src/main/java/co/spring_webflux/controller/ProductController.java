package co.spring_webflux.controller;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;

import co.spring_webflux.entity.CategoryEntity;
import co.spring_webflux.entity.ProductEntity;
import co.spring_webflux.service.ICategoryService;
import co.spring_webflux.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;
    private final ICategoryService categoryService;
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/form")
    public Mono<String> showFormCreate(Model model) {
        model.addAttribute("product", new ProductEntity());
        model.addAttribute("title", "Registrar producto");
        model.addAttribute("categories", categoryService.findAll().collectList());
        return Mono.just("form");
    }

    @GetMapping("/form/{id}")
    public Mono<String> showFormUpdate(@PathVariable String id, Model model) {
        return productService.findById(id).doOnNext(p -> {
            log.info(p.getId());
            model.addAttribute("product", p);
            model.addAttribute("title", "Actualizar producto");
            model.addAttribute("categories", categoryService.findAll().collectList());
        }).defaultIfEmpty(new ProductEntity()).then(Mono.just("form"));

    }

    @PostMapping("/form")
    public Mono<String> save(@Valid ProductEntity productEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Mono.just("form");
        }
        return productService.save(productEntity).thenReturn("redirect:/findAll");
    }

    @GetMapping({ "/findAll", "/" })
    public Mono<String> findAll(Model model) {
        Flux<ProductEntity> products = productService.findAll()
        .flatMap(product -> categoryService.findById(product.getCategoryId())
            .defaultIfEmpty(new CategoryEntity("Sin categoría"))
            .map(category -> {
                product.setCategory(category); // Asignamos la categoría
                return product;
            })
        );
        model.addAttribute("title", "Listado de productos");
        model.addAttribute("products", products);
        return Mono.just("products");
    }

    @GetMapping("/findAllDataDriven")
    public Mono<String> findAllDataDriven(Model model) {
        Flux<ProductEntity> products = productService.findAll().delayElements(Duration.ofMillis(500));
        model.addAttribute("title", "Listado de productos");
        model.addAttribute("products", new ReactiveDataDriverContextVariable(products, 1));
        return Mono.just("products");
    }

    @GetMapping("/findAllFullChunked")
    public Mono<String> findAllFullChunked(Model model) {
        Flux<ProductEntity> products = productService.findAll().repeat(500);
        model.addAttribute("title", "Listado de productos");
        model.addAttribute("products", products);
        return Mono.just("products");
    }

    @GetMapping("/eliminar/{id}")
    public Mono<String> deleteProduct(@PathVariable String id) {
        return productService.findById(id).flatMap(p -> {
            return productService.delete(p);
        }).then(Mono.just("redirect:/findAll"));
    }

}
