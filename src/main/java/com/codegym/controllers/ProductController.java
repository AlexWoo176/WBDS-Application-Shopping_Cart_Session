package com.codegym.controllers;

import com.codegym.model.Product;
import com.codegym.model.ProductForm;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@PropertySource("classpath:global_config_app.properties")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    Environment env;

    @GetMapping("/create-product")
    public ModelAndView showCreateProduct() {
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("productForm", new ProductForm());
        return modelAndView;
    }

    @PostMapping("/create-product")
    public ModelAndView saveProduct(@ModelAttribute("productForm") ProductForm productForm, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("Result Error Occured" + result.getAllErrors());
        }

        MultipartFile multipartFile = productForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();

        try {
            FileCopyUtils.copy(productForm.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Product productObject = new Product(productForm.getName(), productForm.getCode(), productForm.getPrice(), productForm.getDescription(), fileName);

        productService.save(productObject);
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new ProductForm());
        modelAndView.addObject("message", "New Product Created Successfully");
        return modelAndView;
    }



    @GetMapping("/products")
    public ModelAndView listEmployees(@RequestParam("searchName") Optional<String> name, @PageableDefault(value =3) Pageable pageable){
        Page<Product> products;
        if(name.isPresent()){
            products= productService.findAllByNameContaining(name.get(),pageable);
        }else{
            products = productService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("product/list");
        modelAndView.addObject("products", products);
        return modelAndView;
    }
    @GetMapping("/view-product/{id}")
    public String viewProduct(@PathVariable Long id, Model model){
        Product product = productService.findById(id);
        model.addAttribute("product",product);
        return "product/view";
    }
}
