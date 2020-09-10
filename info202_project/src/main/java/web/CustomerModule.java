package web;

import dao.CustomerDAO;
import domain.Customer;
import org.jooby.Jooby;
import org.jooby.Result;
import org.jooby.Status;

/**
 *
 * @author Admin
 */
public class CustomerModule extends Jooby{
    
     public CustomerModule(CustomerDAO customerDao){
   
         get("/api/customers/:username", (req) -> {
             String username = req.param("username").value();
             if (customerDao.getCustomer(username) == null) {
                 return new Result().status(Status.NOT_FOUND);
             } else {
                 return customerDao.getCustomer(username);
             }
         });
        
        post("/api/register", (req, rsp) -> {
            Customer customer = req.body().to(Customer.class);
            customerDao.saveCustomer(customer);
            rsp.status(Status.CREATED);
        });
    }
}
