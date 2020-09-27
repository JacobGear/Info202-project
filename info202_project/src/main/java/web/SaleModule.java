/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.SaleDAO;
import domain.Customer;
import domain.Sale;
import domain.SaleItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.jooby.Jooby;
import org.jooby.Status;

/**
 *
 * @author Admin
 */
public class SaleModule extends Jooby{
    
     public SaleModule(SaleDAO saleDao){
         
         post("/api/sales", (req, rsp) -> {
             Sale sale = req.body().to(Sale.class);
             Customer c = sale.getCustomer();
             Collection<SaleItem> items = new ArrayList<>();
             Collection<SaleItem> si = sale.getItems();
             System.out.println(sale);
             saleDao.save(sale);

             CompletableFuture.runAsync(() -> {
                 try {
                     Email email = new SimpleEmail();
                     email.setHostName("localhost");
                     email.setSmtpPort(2525);
                     email.setFrom("FruitShop@gmail.com");
                     email.setSubject("Thank you for you purchase"
                             + c.getUsername() + "- Fruit Shop");
                     email.setMsg("Hello " + c.getFirstName() + ",\n" + si);
                     email.addTo(c.getEmailAddress());
                     email.send();
                 } catch (EmailException ee) {
                     System.out.println("Email exception");
                 }
             });
             System.in.read();

             rsp.status(Status.CREATED);
         });
        
    }
}