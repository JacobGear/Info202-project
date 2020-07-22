/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Admin
 */
public class ProductCollectionsDAO {
    private static Collection<Product> products = new ArrayList<>();
    
    public void saveProduct(Product p){
        products.add(p);
    }
    
    public Collection<Product> getProducts(){
      return products;
    }
    
    public void removeProduct(Product p){
        products.remove(p);
    }
}
