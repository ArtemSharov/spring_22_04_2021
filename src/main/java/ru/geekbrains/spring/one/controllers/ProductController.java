package ru.geekbrains.spring.one.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.one.model.Product;
import ru.geekbrains.spring.one.repositories.ProductRepository;
import ru.geekbrains.spring.one.services.ProductService;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public String showAllProductsPage(Model model, @RequestParam(name = "p", defaultValue = "1") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        Page<Product> page = productService.findAll(pageIndex - 1, 4);
        model.addAttribute("page", page);
        return "index";
    }

    @GetMapping("/products/{id}")
    public String showProductInfo(@PathVariable(name = "id") Long id, Model model) {
        productService.findOneById(id).ifPresent(p -> model.addAttribute("product", p));
        return "product_info";
    }

    @GetMapping("/products/add")
    public String showCreateProductForm() {
        return "create_product_form";
    }

    @GetMapping("/products/{id}/price/inc")
    public String incrementProductPrice(@PathVariable Long id) {
        productService.incrementPriceById(id, 10);
        return "redirect:/";
    }

    @PostMapping("/products/add")
    public String createNewProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/products/priceFilter")
    public String findAllByPriceBetween(Model model, @RequestParam(name = "p", defaultValue = "1") int pageIndex, @RequestParam(name = "lowerPrice", defaultValue = "0") Integer min, @RequestParam(name = "upperPrice", defaultValue = "10000") Integer max) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        Page<Product> page = productService.findAllByPriceBetween(pageIndex - 1, 4, min, max);
        model.addAttribute("page", page);
        return "index";
    }
    @PostMapping("/products/titleFilter")
    public String findAllByTitle(Model model, @RequestParam(name = "p", defaultValue = "1") int pageIndex, @RequestParam(name = "title") String title) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        Page<Product> page = productService.findAllByTitleLike(pageIndex - 1, 4, title);
        model.addAttribute("page", page);
        return "index";
    }

}
