package dao;

import domain.Customer;
import domain.Product;
import domain.Sale;
import domain.SaleItem;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaleJdbcDAO implements SaleDAO {

    private final String url = DbConnection.getDefaultConnectionUri();

    @Override
    public void save(Sale sale) {

        Connection con = DbConnection.getConnection(url);
        try {
            try (
                    PreparedStatement insertSaleStmt = con.prepareStatement(
                            //**** SQL for saving Sale goes here ****
                            "insert into sale (saleID, customerID, date, status) values (default, ?, ?, ?);",
                            Statement.RETURN_GENERATED_KEYS);
                    
                    PreparedStatement insertSaleItemStmt = con.prepareStatement(
                            //"**** SQL for saving SaleItem goes here 
                            "insert into saleItem (saleID, productID, quantityPurchased, salePrice) values (?,?,?,?)");
                    
                    PreparedStatement updateProductStmt = con.prepareStatement(
                            //"**** SQL for updating product quantity goes here ****");
                            "update Product set quantityInStock = ? where productID = ?");
                    ) {

                // Since saving a Sale involves multiple statements across
                // multiple tables we need to control the transaction ourselves
                // to ensure the DB remains consistent.  Turning off auto-commit
                // effectively starts a new transaction.
                con.setAutoCommit(false);

                Customer customer = sale.getCustomer();

                // #### save the sale ### //
                // add a date to the sale if one doesn't already exist
                if (sale.getDate() == null) {
                    sale.setDate(LocalDateTime.now());
                }

                // convert sale date into to java.sql.Timestamp
                LocalDateTime date = sale.getDate();
                Timestamp timestamp = Timestamp.valueOf(date);

                // ****
                // write code here that saves the timestamp and username in the
                // sale table using the insertSaleStmt statement.
                // ****
                insertSaleStmt.setInt(1, customer.getCustomerID());
                insertSaleStmt.setTimestamp(2, timestamp); 
                insertSaleStmt.setString(3, "N"); 
                insertSaleStmt.executeUpdate();
                
                // get the auto-generated sale ID from the database
                ResultSet rs = insertSaleStmt.getGeneratedKeys();

                Integer saleID = null;

                if (rs.next()) {
                    saleID = rs.getInt(1);
                    sale.setSaleID(saleID);
                } else {
                    throw new DAOException("Problem getting generated sale ID");
                }

                // ## save the sale items ## //
                Collection<SaleItem> items = sale.getItems();

                // ****
                // write code here that iterates through the sale items and
                // saves them using the insertSaleItemStmt statement.
                // ****
                for(SaleItem i : items){
                    insertSaleItemStmt.setInt(1, saleID);
                    insertSaleItemStmt.setString(2, i.getProduct().getProductID());
                    insertSaleItemStmt.setBigDecimal(3, i.getQuantityPurchased());
                    insertSaleItemStmt.setBigDecimal(4, i.getSalePrice());
                    insertSaleItemStmt.executeUpdate();
                }
                
                // ## update the product quantities ## //
                for (SaleItem item : items) {

                    Product product = item.getProduct();
                    // ****
                    // write code here that updates the product quantity using
                    // the updateProductStmt statement.
                    // ****
                    BigDecimal bd = product.getQuantityInStock().subtract(item.getQuantityPurchased());
                    updateProductStmt.setBigDecimal(1, bd);
                    updateProductStmt.setString(2, product.getProductID());
                    updateProductStmt.executeUpdate();
                }

                // commit the transaction
                con.setAutoCommit(true);
            }
        } catch (SQLException ex) {

            Logger.getLogger(SaleJdbcDAO.class.getName()).log(Level.SEVERE, null, ex);

            try {
                // something went wrong so rollback
                con.rollback();

                // turn auto-commit back on
                con.setAutoCommit(true);

                // and throw an exception to tell the user something bad happened
                throw new DAOException(ex.getMessage(), ex);
            } catch (SQLException ex1) {
                throw new DAOException(ex1.getMessage(), ex1);
            }

        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(SaleJdbcDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
