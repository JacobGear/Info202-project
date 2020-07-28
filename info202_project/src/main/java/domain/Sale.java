/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author geaja121
 */
public class Sale {
	private Integer saleID;
	private LocalDateTime date;
	private String status;
	private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public Collection<SaleItem> getItems() {
		return items;
	}
	private Collection<SaleItem> items = new ArrayList<>();

	public Integer getSaleID() {
		return saleID;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public String getStatus() {
		return status;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setItems(Collection<SaleItem> items) {
		this.items = items;
	}

	public void setSaleID(Integer saleID) {
		this.saleID = saleID;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public BigDecimal getTotal(){
		BigDecimal total = BigDecimal.ZERO;
	
		for (SaleItem item : items) {
			total = total.add(item.getItemTotal());
		}
		
		return total;
	}
	
	public void addItem(SaleItem saleItem){
		items.add(saleItem);
	}
	
}
