package com.rsr.product_microservice.port.user.controller;

import com.rsr.product_microservice.core.domain.model.Product;
import com.rsr.product_microservice.core.domain.service.interfaces.IProductService;
import com.rsr.product_microservice.port.utils.exceptions.NoProductsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Product API", description = "API für Produktoperationen")
@Slf4j
public class ProductController {

    @Autowired
    private IProductService productService;


    @Operation(summary = "Gibt alle Produkte zurück", description = "Gibt eine Liste aller Produkte in der Datenbank zurück")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste aller Produkte", content = @Content(schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "404", description = "Keine Produkte gefunden", content = @Content)
    })
    @GetMapping("/product/all")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Product> getAllProducts() throws NoProductsException {
        List<Product> products = productService.getAllProducts();
        log.info(String.format("Returning all Products. Number of Products -> %s", products.size()));
        return products;
    }


    @Operation(summary = "Gibt ein Produkt nach ID zurück", description = "Gibt ein Produkt anhand der angegebenen ID zurück")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produkt gefunden", content = @Content(schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "404", description = "Produkt nicht gefunden", content = @Content)
    })
    @GetMapping("/product/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Product getProductById(@PathVariable("productId") UUID productId) {
        Product productToReturn = productService.getProductById(productId);
        log.info("Returning Product with Id: {}", productId);
        return productToReturn;
    }

}
