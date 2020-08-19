/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.ProductDAO;
import domain.Product;
import helpers.SimpleListModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import static org.assertj.swing.core.matcher.DialogMatcher.withTitle;
import static org.assertj.swing.core.matcher.JButtonMatcher.withText;
import org.assertj.swing.fixture.DialogFixture;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
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
    
    Product apple = new Product("1234", "apple", "a description", "fruit", BigDecimal.valueOf(1.99), BigDecimal.valueOf(20));
    Product orange = new Product("4321", "orange", "a description", "fruit", BigDecimal.valueOf(2.99), BigDecimal.valueOf(200));

    @BeforeEach
    public void setUp() {
        robot = BasicRobot.robotWithNewAwtHierarchy();

        robot.settings().delayBetweenEvents(75);

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
        
        SimpleListModel model = (SimpleListModel) fixture.list("productsModel").target().getModel();
        
        //from here we've opened the dialog 
        
        assertTrue("list contains the expected product", model.contains(apple));
        assertTrue("list contains the expected product", model.contains(orange));
        assertEquals("list contains the correct number of products", 2, model.getSize());
    }
    
     @Test
    public void testDeleteProducts() {
        ProductViewer dialog = new ProductViewer(null, true, dao);

        fixture = new DialogFixture(robot, dialog);

        fixture.show().requireVisible();

        fixture.click();
        
        fixture.list().selectItem(orange.toString());
        
        fixture.button("btnDelete").click();
        
        DialogFixture confirmDialog = fixture.dialog(withTitle("Select an Option").andShowing()).requireVisible();
        
        confirmDialog.button(withText("Yes")).click();
        
        ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);

        // verify that the DAO.delete method was called, and capture the deleted student
        verify(dao).removeProduct(argument.capture());
        
        // retrieve the deleted student from the captor
        Product removedProduct = argument.getValue();

        assertThat(removedProduct, is(orange));        
    }
    

    
}
