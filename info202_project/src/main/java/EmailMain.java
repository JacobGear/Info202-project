/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import domain.Customer;
import domain.Product;
import domain.Sale;
import domain.SaleItem;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Admin
 */
public class EmailMain {
    public static void main(String args[]) throws EmailException, IOException {
        Customer c = new Customer(8246, "pts", "Harry", "Potter", "apples123", 
                "Harry@gmail.com", "63 street");
        Product p = new Product("356236", "Apple", "description", "Fruit", 
                BigDecimal.valueOf(2.99), BigDecimal.valueOf(100));
        Sale sale = new Sale(12314, LocalDateTime.MAX, "N", c);
        SaleItem si = new SaleItem(BigDecimal.valueOf(5), BigDecimal.valueOf(2.99), p, sale);
        Collection<SaleItem> s = new ArrayList();
        s.add(si);
        
        CompletableFuture.runAsync(() -> {
            try{
            Email email = new SimpleEmail();
            email.setHostName("localhost");
            email.setSmtpPort(2525);
            email.setFrom("FruitShop@gmail.com");
            email.setSubject("Thank you for you purchase" + 
                    c.getUsername()+ "- Fruit Shop");
            email.setMsg("Hello " + c.getFirstName() + ",\n" + s);
            email.addTo(c.getEmailAddress());
            email.send();
            } catch(EmailException ee){
                System.out.println("Email exception");
            }
        });
        System.in.read();
        
    }
}

/*CompletableFuture.runAsync(() -> {
            try{
            Email email = new SimpleEmail();
            email.setHostName("localhost");
            email.setSmtpPort(2525);
            email.setFrom("FruitShop@gmail.com");
            email.setSubject("Thank you for you purchase" + 
                    c.getUsername()+ "- Fruit Shop");
            email.setMsg("Hello " + c.getFirstName() + ",\n" + "\tProduct: " 
            + si.getProduct().getName() + "\n\tPrice: " + si.getSalePrice() + 
                    "\n\tQuantity: " + si.getQuantityPurchased() + "\n\tTotal: " +
                    si.getItemTotal());
            email.addTo(c.getEmailAddress());
            email.send();
            } catch(EmailException ee){
                System.out.println("Email exception");
            }
        });
        System.in.read(); */