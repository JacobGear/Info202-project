/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author geaja121
 */
public class SaleItem {
	private BigDecimal quantityPurchased;
	private BigDecimal salePrice;
	private Product product;
        private Sale sale;

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
		BigDecimal value = salePrice.multiply(quantityPurchased);
                BigDecimal total = value.setScale(2, RoundingMode.CEILING);
		return total;
	}

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "SaleItem{" + "quantityPurchased=" + quantityPurchased + ", salePrice=" + salePrice + ", product=" + product + ", sale=" + sale + '}';
    }
    
    
    
        
        
}
