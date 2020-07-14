/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;

/**
 *
 * @author geaja121
 */
public class SaleItem {
	private BigDecimal quantityPurchased;
	private BigDecimal salePrice;
	private Product product;

	public BigDecimal getQuantityPurchased() {
		return quantityPurchased;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setQuantityPurchased(BigDecimal quantityPurchased) {
		this.quantityPurchased = quantityPurchased;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	
	public BigDecimal getItemTotal(){
		BigDecimal total = salePrice.multiply(quantityPurchased);
		return total;
	}
}
