package com.example.product;


import com.example.product.Repos.ProductRepos;
import com.example.product.entites.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ProductApplication implements CommandLineRunner {

    @Autowired
    ProductRepos repos;
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);

    }


    @Override
    public void run(String... args) throws Exception {
        repos.save(new Product(Long.valueOf(1),299.0,"Souris"));
        repos.save(new Product(Long.valueOf(2),499.0,"Casque"));
        repos.save(new Product(Long.valueOf(3),789.0,"Clavier"));
        List<Product> Products =repos.findAll();
        Products.forEach(product -> {
            System.out.println(product);

        });
        System.out.println("--------------------------");
        List<Product> Products2 =repos.findProductByPrixGreaterThan(709.0);
        Products2.forEach(product -> {
            System.out.println(product.getId());
            System.out.println(product.getPrix());
            System.out.println(product.getName());


        });
        System.out.println("--------------------------");
        Product startWithH = repos.search("H%");
        System.out.println(startWithH);
        System.out.println("--------------------------");
        List<Product> productContainsA=repos.findProductByNameContains("a");
        productContainsA.forEach(p->{
            System.out.println(p.getName());
            repos.delete(p);

        });
    }
}