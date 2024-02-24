package org.example;

public class Order {
    private Cart cart;
    private String shippingAddress;

    public Order(Cart cart, String shippingAddress) {
        this.cart = cart;
        this.shippingAddress = shippingAddress;
    }

    // Геттери для отримання інформації про замовлення
    public Cart getCart() {
        return cart;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }
}
