package business.entity;

import business.entity.enum_type.Size;

import java.io.Serializable;

public class CartItem implements Serializable {
    private int id;
    private String productId;
    private Size size;
    private int quantity;
    private double price;

    /**Constructor*/
    public CartItem() {
    }

    public CartItem(int id, String productId, Size size, int quantity, double price) {
        this.id = id;
        this.productId = productId;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
    }

    /**Getter & Setter*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CartIem{" +
                "id=" + id +
                ", productId='" + productId + '\'' +
                ", size=" + size +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}