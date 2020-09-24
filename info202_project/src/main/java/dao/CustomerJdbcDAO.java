/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * https://javaconceptoftheday.com/difference-between-executequery-executeupdate-execute-in-jdbc/
 * @author geaja121
 */

/**
 *
 * @author Admin
 */
public class CustomerJdbcDAO implements CustomerDAO{
    
    private String dbUri = DbConnection.getDefaultConnectionUri();

    public CustomerJdbcDAO() {
    }

    public CustomerJdbcDAO(String dbUri) {
        this.dbUri = dbUri;
    }
    
    @Override
    public void saveCustomer(Customer customer) {
        String sql = "insert into Customer (username, firstName, surname, password, emailAddress, shippingAddress) values (?,?,?,?,?,?)";

        try (
                // get connection to database
                Connection dbCon = DbConnection.getConnection(dbUri);
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            // copy the data from the product domain object into the SQL parameters
            stmt.setString(1, customer.getUsername());
            stmt.setString(2, customer.getFirstName());
            stmt.setString(3, customer.getSurname());
            stmt.setString(4, customer.getPassword());
            stmt.setString(5, customer.getEmailAddress());
            stmt.setString(6, customer.getShippingAddress());

            stmt.executeUpdate();  // execute the statement

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public void SaveCustomer(Customer customer){}

    @Override
    public Customer getCustomer(String username) {
        String sql = "select * from customer where username = ?";

        try (
                // get connection to database
                Connection dbCon = DbConnection.getConnection(dbUri);
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            

            //set the ? in the sql statement
            stmt.setString(1, username);
            
            // execute the query
            ResultSet rs = stmt.executeQuery();
           
            Customer customer = new Customer();

            // iterate through the query results
            while (rs.next()) {

                // get the data out of the query
                Integer customerID = rs.getInt("customerID");
                String userName = rs.getString("username");
                String firstName = rs.getString("firstName");
                String surname = rs.getString("surname");
                String password = rs.getString("password");
                String emailAddress = rs.getString("emailAddress");
                String shippingAddress = rs.getString("shippingAddress");

                // use the data to create a student object
                customer = new Customer(customerID, userName, firstName, surname, password, emailAddress, shippingAddress);
            }

            return customer;

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Boolean validateCredentials(String username, String password) {
        String sql = "select username, password from customer where username = ?";

        try (
                // get connection to database
                Connection dbCon = DbConnection.getConnection(dbUri);
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            // execute the query
            ResultSet rs = stmt.executeQuery();

            //set the ? in the sql statement
            stmt.setString(1, username);
           
            //List<Customer> customers = new ArrayList<>();
            Map<String, Customer> customers = new HashMap<>();

            // iterate through the query results
            while (rs.next()) {

                // get the data out of the query
                Integer customerID = rs.getInt("customerID");
                String userName = rs.getString("username");
                String firstName = rs.getString("firstName");
                String surname = rs.getString("surname");
                String passWord = rs.getString("password");
                String emailAddress = rs.getString("emailAddress");
                String shippingAddress = rs.getString("shippingAddress");

                // use the data to create a student object
                Customer c = new Customer(customerID, userName, firstName, surname, passWord, emailAddress, shippingAddress);
                customers.put(userName, c);
            }
            if (customers.containsKey(username)) {
                return customers.get(username).getPassword().equals(password);
            } else {
                return false;
            }

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new DAOException(ex.getMessage(), ex);
        }
    }
}
