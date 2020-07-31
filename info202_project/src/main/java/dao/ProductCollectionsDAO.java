/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
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
    private static final Collection<Product> products = new HashSet<>();
    private static final Collection<String> categories = new HashSet<>();
    private static final Map<String, Product> ids = new HashMap<>();
    private static final Multimap<String, Product> viewerCategories = HashMultimap.create();
    
    public void saveProduct(Product p) {
        products.add(p);
        
        String category = p.getCategory();
        categories.add(category);
        viewerCategories.put(category, p);
        
        String id = p.getProductID();
        ids.put(id, p); // match a product with a key        
    }
    
    public Collection<Product> getProducts(){
      return products;
    }
    
    public void removeProduct(Product p){
        products.remove(p);
    }
    
    public Collection<String> getCategories() {
        return categories;
    }
    
    public Map<String, Product> getIds(){
        return ids;
    }
    
    public Multimap<String, Product> getViewerCategories(){
        return viewerCategories;
    }

    public Product searchByID(String productID) {
        Product p;
        if (ids.containsKey(productID)) {
            // Map.get() will get the Product domain, with the key
            p = ids.get(productID); 
        } else {
            return null;
        }
        return p;
    }
    
    public Collection<Product> filterByCategory(String category) {
        Collection<Product> p;
        if (!viewerCategories.containsKey(category)) {
            return null;
        } else {
            p = viewerCategories.get(category);
        }
        return p;
    }
}
