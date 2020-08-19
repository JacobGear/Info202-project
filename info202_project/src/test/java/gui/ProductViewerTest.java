/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.ProductDAO;
import domain.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
/**
 *
 * @author Admin
 */
public class ProductViewerTest {
    
    private ProductDAO dao;
    private DialogFixture fixture;
    private Robot robot;

    @BeforeEach
    public void setUp() {
        robot = BasicRobot.robotWithNewAwtHierarchy();

        robot.settings().delayBetweenEvents(75);

        Product apple = new Product("1234", "apple", "a description", "fruit", BigDecimal.valueOf(1.99), BigDecimal.valueOf(20));
        Product orange = new Product("4321", "orange", "a description", "fruit", BigDecimal.valueOf(2.99), BigDecimal.valueOf(200));

        Collection<Product> products = new ArrayList<>();
        products.add(apple);
        products.add(orange);

        dao = mock(ProductDAO.class);

       when(dao.getProducts()).thenReturn(products);
    }

    @AfterEach
    public void tearDown() {
        fixture.cleanUp();
    }

    @Test
    public void testViewAllProducts() {
        ProductViewer dialog = new ProductViewer(null, true, dao);

        fixture = new DialogFixture(robot, dialog);

        fixture.show().requireVisible();

        fixture.click();
        
        //from here we've opened the dialog

        
    }
    
     @Test
    public void testDeleteProducts() {
        ProductViewer dialog = new ProductViewer(null, true, dao);

        fixture = new DialogFixture(robot, dialog);

        fixture.show().requireVisible();

        fixture.click();
        
        // Click on a product
        
        fixture.button("btnDelete").click();
        
        ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);

        // verify that the DAO.delete method was called, and capture the deleted student
        verify(dao).removeProduct(argument.capture());
        
        // retrieve the deleted student from the captor
        Product removedProduct = argument.getValue();

        assertThat("Ensure the product was removed", dao.getProducts(), not(hasItem(removedProduct)));  
        
        
        
      
    }
    

    
}
