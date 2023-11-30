package model.shoppingcart;

import model.product.Product;

public class ShoppingCartItem {
    
    private Product product;
    private int quantify;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantify() {
        return quantify;
    }

    public void setQuantify(int quantify) {
        this.quantify = quantify;
    }
    
    
}
