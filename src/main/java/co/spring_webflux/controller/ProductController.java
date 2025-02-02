package co.spring_webflux.controller;

import java.time.Duration;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;

import co.spring_webflux.entity.ProductEntity;
import co.spring_webflux.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Controller
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductRepository productRepository;

    @GetMapping({"/findAll", "/"})
    public String findAll(Model model) {
        Flux<ProductEntity> products = productRepository.findAll();
        model.addAttribute("title", "Listado de productos");
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/findAllDataDriven")
    public String findAllDataDriven(Model model) {
        Flux<ProductEntity> products = productRepository.findAll().delayElements(Duration.ofMillis(500));
        model.addAttribute("title", "Listado de productos");
        model.addAttribute("products", new ReactiveDataDriverContextVariable(products, 1));
        return "products";
    }

    @GetMapping("/findAllFullChunked")
    public String findAllFullChunked(Model model) {
        Flux<ProductEntity> products = productRepository.findAll().repeat(500);
        model.addAttribute("title", "Listado de productos");
        model.addAttribute("products",products);
        return "products";
    }
}
