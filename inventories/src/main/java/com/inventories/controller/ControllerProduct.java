package com.inventories.controller;

import com.inventories.exception.ResourceNotFoundException;
import com.inventories.modelo.Product;
import com.inventories.servicio.ServiceProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("app")
@CrossOrigin(value = "http://localhost:4200")
public class ControllerProduct {
    public static final Logger logger = LoggerFactory.getLogger(ControllerProduct.class);
    @Autowired
    private ServiceProduct serviceProduct;

    @GetMapping("/products")
    public List<Product> getProducts(){
        List<Product> products = this.serviceProduct.productsList();
        logger.info("Products obtained");
        products.forEach(product -> logger.info(product.toString()));
        return products;
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product producto){
        logger.info("Producto a agregar: " + producto);
        return this.serviceProduct.productSave(producto);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        Product product = this.serviceProduct.searchProductById(id);
        if(product != null) {
            return ResponseEntity.ok(product);
        }else{
         throw new ResourceNotFoundException("No se encontré el ID");
        }
    }
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable int id,
            @RequestBody Product productReceived){
        Product product = this.serviceProduct.searchProductById(id);
        if(product == null){
            throw new ResourceNotFoundException("No se encontro el id: " + id);
        }
        product.setDescription(productReceived.getDescription());
        product.setPrice(productReceived.getPrice());
        product.setExistence(productReceived.getExistence());
        this.serviceProduct.productSave(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Map<String, Boolean>>
        deleteProduct(@PathVariable int id){
        Product product = serviceProduct.searchProductById(id);
        if(product == null){
          throw new ResourceNotFoundException("No se encontro el id: " + id);
        }
        this.serviceProduct.deleteProductById(product.getIdProduct());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
