/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.time.LocalDateTime;

/**
 *
 * @author geaja121
 */
public class Sale {
	Integer saleID;
	LocalDateTime date;
	String status;

	public Integer getSaleID() {
		return saleID;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public String getStatus() {
		return status;
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
	
	
}
