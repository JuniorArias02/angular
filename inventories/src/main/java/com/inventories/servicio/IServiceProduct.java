package com.inventories.servicio;

import com.inventories.modelo.Product;

import java.util.List;

public interface IServiceProduct {
    public List<Product> productsList();
    public Product searchProductById(Integer idProduct);
    public Product productSave(Product product);
    public void deleteProductById(Integer idProduct);

}
