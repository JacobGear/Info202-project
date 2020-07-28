/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class ProductCollectionsDAO {
    private static Collection<Product> products = new HashSet<>();
    private static Collection<String> categories = new HashSet<>();
	 private static Map<String, Product> ids = new HashMap<>();
    
    public void saveProduct(Product p){
        products.add(p);
        String category = p.getCategory();
        categories.add(category);
		  String id = p.getProductID();
		  ids.put(id, p);
    }
    
    public Collection<Product> getProducts(){
      return products;
    }
    
    public void removeProduct(Product p){
        products.remove(p);
    }
    
    public Collection<String> getCategories(){
        return categories;
    }
	 
	 public Product searchByID(String productID){
		 if(ids.containsKey(productID)){
			 return Product;
		 }else{
			 return null;
		 }
	 }
}
