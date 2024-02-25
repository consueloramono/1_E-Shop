package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Cart {
    private List<Product> products;

    public Cart() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public java.util.List<Product> getProducts() {
        return products;
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }
    public void displayCart() {
        System.out.println("Товари у кошику:");
        for (Product product : this.getProducts()) {
            System.out.println("- " + product.getName() + " - " + product.getPrice() + " грн");
        }
        System.out.println("Загальна вартість: " + this.getTotalPrice() + " грн");
    }

    public void removeProductFromCart(int productId) {
        Iterator<Product> iterator = this.getProducts().iterator();
        boolean removed = false; // Флаг, щоб відстежити, чи відбулося хоча б одне видалення
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getId() == productId) {
                iterator.remove(); // Безпечне видалення елемента зі списку
                System.out.println("Видалено товар з ID: " + productId);
                removed = true;
            }
        }
        if (!removed) {
            System.out.println("В корзині немає товару з ID: " + productId);
        }
    }
}