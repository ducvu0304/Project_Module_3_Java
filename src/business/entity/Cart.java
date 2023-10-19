package business.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Cart implements Serializable  {
    private int cartId;
    private int accountId;
    private double total;
    private String address;
    private String phoneNumber;
    private LocalDateTime payDate;
    private LocalDateTime deliverDate;

    /**Constructor*/
    public Cart() {
    }

    public Cart(int accountId, int cartId, double total, String address, String phoneNumber, LocalDateTime payDate, LocalDateTime deliverDate) {
        this.accountId = accountId;
        this.cartId = cartId;
        this.total = total;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.payDate = payDate;
        this.deliverDate = deliverDate;
    }

    /**Getter & Setter*/
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getPayDate() {
        return payDate;
    }

    public void setPayDate(LocalDateTime payDate) {
        this.payDate = payDate;
    }

    public LocalDateTime getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(LocalDateTime deliverDate) {
        this.deliverDate = deliverDate;
    }

    /**Display*/
    @Override
    public String toString() {
        return "Cart{" +
                "accountId=" + accountId +
                ", cartId=" + cartId +
                ", total=" + total +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", payDate=" + payDate +
                ", deliverDate=" + deliverDate +
                '}';
    }
}
