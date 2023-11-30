package model.sale;

import java.sql.Timestamp;
import java.util.List;

public class Sale {

    private int id;
    private Timestamp dateTime;
    private int userId;
    private List<SaleProduct> products;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<SaleProduct> getProducts() {
        return products;
    }

    public void setProducts(List<SaleProduct> products) {
        this.products = products;
    }

    
}
