/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.math.BigDecimal;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Admin
 */
public class ProductCollectionsDAOTest {
    
    private ProductCollectionsDAO pDAO;
    private Product product1;
    private Product product2;
    private Product product3;    
    
    @BeforeEach
    public void setUp() {
        pDAO = new ProductCollectionsDAO();
        
        BigDecimal listprice, quantity;
        product1 = new Product();
        product1.setProductID("1234");
        product1.setName("Stick");
        product1.setDescription("Cool looking stick");
        product1.setCategory("Item 1");
        product1.setListPrice(listprice = new BigDecimal("11.99"));
        product1.setQuantityInStock(quantity = new BigDecimal("100"));
        
        BigDecimal listprice2, quantity2;
        product2 = new Product();
        product2.setProductID("9876");
        product2.setName("Bottle");
        product2.setDescription("Cool looking bottle");
        product2.setCategory("Item 1");
        product2.setListPrice(listprice2 = new BigDecimal("3.99"));
        product2.setQuantityInStock(quantity2 = new BigDecimal("50"));
        
        BigDecimal listprice3, quantity3;
        product3 = new Product();
        product3.setProductID("46346");
        product3.setName("Glass");
        product3.setDescription("Cool looking glass");
        product3.setCategory("Item 3");
        product3.setListPrice(listprice2 = new BigDecimal("45.50"));
        product3.setQuantityInStock(quantity2 = new BigDecimal("7"));
        
        pDAO.saveProduct(product1);
        pDAO.saveProduct(product2);
    }
    
    @AfterEach
    public void tearDown() {
        pDAO.removeProduct(product1);
        pDAO.removeProduct(product2);
    }

    @Test
    public void testSaveProduct() {
        assertThat(pDAO.getProducts(), hasItem(product1));
        assertThat(pDAO.getProducts(), hasItem(product2));
        assertThat(pDAO.getProducts(), hasSize(2));
    }

    @Test
    public void testGetProducts() {
        assertThat(pDAO.getProducts(), hasItem(product1));
    }
    
    @Test
    public void testGetCategories() {
        assertThat(pDAO.getCategories(), hasItem(product1.getCategory()));
        assertThat(pDAO.getCategories(), hasItem(product2.getCategory()));
        int size = pDAO.getCategories().size();
        assertThat(pDAO.getCategories(), hasSize(size));
    }

    @Test
    public void testRemoveProduct() {
        pDAO.removeProduct(product1);
        assertThat(pDAO.getProducts(), not(hasItem(product1)));
        assertThat(pDAO.getProducts(), hasSize(1));
    }
    
    
}
