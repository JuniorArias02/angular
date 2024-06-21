package com.inventories.servicio;

import com.inventories.modelo.Product;
import com.inventories.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceProduct  implements IServiceProduct{

    @Autowired
    private IProductRepository repositoryProduct;

    @Override
    public List<Product> productsList() {
       return this.repositoryProduct.findAll();
    }
    @Override
    public Product searchProductById(Integer idProduct) {
        Product product = this.repositoryProduct.findById(idProduct).orElse(null);
        return product;
    }
    @Override
    public Product productSave(Product product) {
        return this.repositoryProduct.save(product);
    }
    @Override
    public void deleteProductById(Integer idProduct) {
        this.repositoryProduct.deleteById(idProduct);
    }

}
