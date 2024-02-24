package org.example;
public class Main {
    public static void main(String[] args) {
        // Створення категорій
        Category electronics = new Category("Електроніка");
        Category clothing = new Category("Одяг");

        // Створення товарів
        Product laptop = new Product(1, "Ноутбук", 1000.0, "Потужний ноутбук для роботи та розваг", electronics);
        Product shirt = new Product(2, "Сорочка", 30.0, "Кежуальна сорочка для щоденного носіння", clothing);

        // Додавання товарів до категорій
        electronics.addProduct(laptop);
        clothing.addProduct(shirt);

        // Створення кошика
        Cart cart = new Cart();

        // Додавання товарів до кошика
        cart.addProduct(laptop);
        cart.addProduct(shirt);

        // Виведення товарів у кошику
        System.out.println("Товари у кошику:");
        for (Product product : cart.getProducts()) {
            System.out.println("- " + product.getName() + " (" + product.getCategory().getName() + "): " + product.getPrice());
        }

        // Виведення загальної суми товарів у кошику
        System.out.println("Загальна сума товарів у кошику: " + cart.getTotalPrice());

        // Створення замовлення
        Order order = new Order(cart, "Вулиця Примерна, 123");

        // Виведення інформації про замовлення
        System.out.println("Інформація про замовлення:");
        System.out.println("Адреса доставки: " + order.getShippingAddress());
        System.out.println("Товари:");
        for (Product product : order.getCart().getProducts()) {
            System.out.println("- " + product.getName() + " (" + product.getCategory().getName() + "): " + product.getPrice());
        }
        System.out.println("Загальна вартість замовлення: " + cart.getTotalPrice());
    }
}