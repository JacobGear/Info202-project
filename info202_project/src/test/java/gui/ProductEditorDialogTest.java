package gui;

import dao.ProductDAO;
import domain.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class ProductEditorDialogTest {

    private ProductDAO dao;
    private DialogFixture fixture;
    private Robot robot;

    @BeforeEach
    public void setUp() {
        robot = BasicRobot.robotWithNewAwtHierarchy();

        robot.settings().delayBetweenEvents(75);

        Collection<String> categories = new ArrayList<>();
        categories.add("fruit");
        categories.add("vegetables");

        dao = mock(ProductDAO.class);

        when(dao.getCategories()).thenReturn(categories);
    }

    @AfterEach
    public void tearDown() {
        fixture.cleanUp();
    }

    @Test
    public void testSave() {
        ProductEditor dialog = new ProductEditor(null, true, dao);

        fixture = new DialogFixture(robot, dialog);

        fixture.show().requireVisible();

        fixture.click();

        fixture.textBox("txtId").enterText("1234");
        fixture.textBox("txtName").enterText("Orange");
        fixture.textBox("txtDescription").enterText("A Description");
        fixture.comboBox("cmboCategory").selectItem("fruit");
        fixture.textBox("txtPrice").enterText("1.99");
        fixture.textBox("txtQuantity").enterText("20");

        fixture.button("btnSave").click();

        ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);

        verify(dao).saveProduct(argument.capture());

        Product savedProduct = argument.getValue();

        assertThat("Ensure the ID was saved", savedProduct.getProductID(), is("1234"));
        assertThat("Ensure the name was saved", savedProduct.getName(), is("Orange"));
        assertThat("Ensure the description was saved", savedProduct.getDescription(), is("A Description"));
        assertThat("Ensure the category was saved", savedProduct.getCategory(), is("fruit"));
        assertThat("Ensure the price was saved", savedProduct.getListPrice(), is(BigDecimal.valueOf(1.99)));
        assertThat("Ensure the quantity was saved", savedProduct.getQuantityInStock(), is(BigDecimal.valueOf(20)));
    }

}
