/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * https://javaconceptoftheday.com/difference-between-executequery-executeupdate-execute-in-jdbc/
 * @author geaja121
 */
public class ProductJdbcDAO implements ProductDAO {

    private String dbUri = DbConnection.getDefaultConnectionUri();

    public ProductJdbcDAO() {
    }

    public ProductJdbcDAO(String dbUri) {
        this.dbUri = dbUri;
    }

    @Override
    public Collection<Product> filterByCategory(String category) {
        String sql = "select * from product where category = ?";
        
        try (
                // get connection to database
                Connection dbCon = DbConnection.getConnection(dbUri);
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            // execute the query
     
            stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();
            Collection<Product> products = new ArrayList<>();
            
            // iterate through the query results
            while (rs.next()) {

                // get the data out of the query
                String id = rs.getString("productID");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String cat= rs.getString("category");
                BigDecimal listPrice = rs.getBigDecimal("listPrice");
                BigDecimal quantityInStock = rs.getBigDecimal("quantityInStock");

                // use the data to create a student object
                Product p = new Product(id, name, description, cat, listPrice, quantityInStock);
                products.add(p);

            }

            return products;
            
        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Collection<String> getCategories() {
        String sql = "select distinct category from Product";

        try (
                // get connection to database
                Connection dbCon = DbConnection.getConnection(dbUri);
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            // execute the query
            ResultSet rs = stmt.executeQuery();

            // Using a List to preserve the order in which the data was returned from the query.
            List<String> categories = new ArrayList<>();

            while (rs.next()) {
                String category = rs.getString("category");
                categories.add(category);
            }

            return categories;

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Map<String, Product> getIds() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Product> getProducts() {
        String sql = "select * from product";

        try (
                // get connection to database
                Connection dbCon = DbConnection.getConnection(dbUri);
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            // execute the query
            ResultSet rs = stmt.executeQuery();

            // Using a List to preserve the order in which the data was returned from the query.
            List<Product> products = new ArrayList<>();

            // iterate through the query results
            while (rs.next()) {

                // get the data out of the query
                String id = rs.getString("productID");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String category = rs.getString("category");
                BigDecimal listPrice = rs.getBigDecimal("listPrice");
                BigDecimal quantityInStock = rs.getBigDecimal("quantityInStock");

                // use the data to create a student object
                Product p = new Product(id, name, description, category, listPrice, quantityInStock);

                // and put it in the collection
                products.add(p);
            }

            return products;

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void removeProduct(Product p) {
        String sql = "delete from Product where productID = ?";
        
        try (
                // get connection to database
                Connection dbCon = DbConnection.getConnection(dbUri);
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            // execute the query
       
            
            stmt.setString(1, p.getProductID());
            stmt.executeUpdate();

            

            

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void saveProduct(Product p) {
        String sql = "insert into product (productID, name, description, category, listPrice, quantityInStock) values (?,?,?,?,?,?)";

        try (
                // get connection to database
                Connection dbCon = DbConnection.getConnection(dbUri);
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            // copy the data from the product domain object into the SQL parameters
            stmt.setString(1, p.getProductID());
            stmt.setString(2, p.getName());
            stmt.setString(3, p.getDescription());
            stmt.setString(4, p.getCategory());
            stmt.setBigDecimal(5, p.getListPrice());
            stmt.setBigDecimal(6, p.getQuantityInStock());

            stmt.executeUpdate();  // execute the statement

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Product searchByID(String productID) {
        String sql = "select * from product where productID = ?";
        
        try (
                // get connection to database
                Connection dbCon = DbConnection.getConnection(dbUri);
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            // execute the query
     
            stmt.setString(1, productID);
            //stmt.executeQuery();
            ResultSet rs = stmt.executeQuery();
            Product p = new Product();
            
            // iterate through the query results
            while (rs.next()) {

                // get the data out of the query
                String id = rs.getString("productID");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String category = rs.getString("category");
                BigDecimal listPrice = rs.getBigDecimal("listPrice");
                BigDecimal quantityInStock = rs.getBigDecimal("quantityInStock");

                // use the data to create a student object
                p = new Product(id, name, description, category, listPrice, quantityInStock);

            }

            return p;
            
        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new RuntimeException(ex);
        }
    }

}
