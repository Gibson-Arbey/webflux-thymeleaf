package co.spring_webflux.controller;

import java.time.Duration;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;

import co.spring_webflux.entity.ProductEntity;
import co.spring_webflux.service.IProductService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    @GetMapping("/form")
    public Mono<String> showForm(Model model) {
        model.addAttribute("product", new ProductEntity());
        model.addAttribute("title", "Registrar producto");
        return Mono.just("form");
    }

    @PostMapping("/form")
    public Mono<String> save(ProductEntity productEntity) {
        return productService.save(productEntity).thenReturn("redirect:/findAll");
    }

    @GetMapping({ "/findAll", "/" })
    public Mono<String> findAll(Model model) {
        Flux<ProductEntity> products = productService.findAll();
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

}
