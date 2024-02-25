package org.example;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Створення категорій
        Category electronics = new Category("Електроніка");
        Category clothing = new Category("Одяг");

        // Створення товарів
        Product laptop = new Product(1, "Ноутбук", 1000.0, "Потужний ноутбук MSI GF63", electronics);
        Product shirt = new Product(2, "Сорочка", 30.0, "Кежуальна сорочка Stone Island", clothing);

        // Додавання товарів до категорій
        electronics.addProduct(laptop);
        clothing.addProduct(shirt);
        List<Category> categories = new ArrayList<>();
        categories.add(electronics);
        categories.add(clothing);
        // Створення кошика
        Cart cart = new Cart();

        // Створення списку замовлень
        List<Order> ordersHistory = new ArrayList<>();

        // Взаємодія з користувачем
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("Меню:");
            System.out.println("1. Переглянути список товарів");
            System.out.println("2. Додати товар до кошика");
            System.out.println("3. Переглянути кошик");
            System.out.println("4. Видалити товар з кошика");
            System.out.println("5. Зробити замовлення");
            System.out.println("6. Пошук товарів");
            System.out.println("7. Переглянути історію замовлень");
            System.out.println("8. Вийти з програми");
            System.out.print("Виберіть опцію: ");
            try {
                int option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        System.out.println("Список товарів:");
                        for (Category category : categories) {
                            System.out.println(category.getName() + ":");
                            for (Product product : category.getProducts()) {
                                System.out.println("-Назва: " + product.getName() + " -Ціна: " + product.getPrice() + "грн. -ID:" + product.getId());
                            }
                            System.out.println();
                        }
                        break;
                    case 2:
                        System.out.println("Введіть ID товару, який ви хочете додати до кошика:");
                        try {
                            int productIdToAdd = Integer.parseInt(scanner.nextLine()); // Зчитуємо ID товару як рядок і конвертуємо в ціле число
                            Product selectedProduct = Product.findProductById(productIdToAdd, categories);
                            if (selectedProduct != null) {
                                cart.addProduct(selectedProduct);
                                System.out.println(selectedProduct.getName() + " додано до кошика.");
                            } else {
                                System.out.println("Товар з таким ID не знайдено.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Ви ввели неправильний формат ID товару. Будь ласка, введіть ціле число.");
                        }
                        break;

                    case 3:
                        cart.displayCart();
                        break;
                    case 4:
                        System.out.println("Введіть ID товару, який ви хочете видалити з кошика:");
                        try {
                            int productIdToRemove = Integer.parseInt(scanner.nextLine()); // Зчитуємо ID товару як рядок і конвертуємо в ціле число
                            cart.removeProductFromCart(productIdToRemove);
                        } catch (NumberFormatException e) {
                            System.out.println("Ви ввели неправильний формат ID товару. Будь ласка, введіть ціле число.");
                        }
                        break;
                    case 5:
                        if (!cart.getProducts().isEmpty()) {
                            System.out.println("Введіть адресу доставки:");
                            String shippingAddress = scanner.nextLine();
                            Order order = new Order(cart, shippingAddress);
                            ordersHistory.add(order);
                            System.out.println("Замовлення успішно оформлено!");
                            System.out.println("Адреса доставки: " + order.getShippingAddress());
                            cart.displayCart();
                            cart = new Cart(); // Очистити кошик після замовлення
                        } else {
                            System.out.println("Кошик порожній. Додайте товари до кошика.");
                        }
                        break;
                    case 6:
                        System.out.println("Пошук товару:");
                        System.out.println("1. Пошук за назвою");
                        System.out.println("2. Пошук за категорією");
                        System.out.print("Виберіть опцію: ");
                        String searchOptionStr = scanner.nextLine();
                        try {
                            int searchOption = Integer.parseInt(searchOptionStr); // Конвертування рядка в ціле число
                            switch (searchOption) {
                                case 1 -> {
                                    System.out.print("Введіть назву товару для пошуку: ");
                                    Product.searchProductsByName(scanner.nextLine(), categories);
                                }
                                case 2 -> {
                                    System.out.print("Введіть назву категорії для пошуку: ");
                                    Product.searchProductsByCategory(scanner.nextLine(), categories);
                                }
                                default -> System.out.println("Невідома опція. Спробуйте ще раз.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Ви ввели неправильний формат опції. Будь ласка, введіть ціле число.");
                        }
                        break;
                    case 7:
                        System.out.println("Історія замовлень:");
                        if (ordersHistory.isEmpty()) {
                            System.out.println("Історія замовлень порожня.");
                        } else {
                            for (Order order : ordersHistory) {
                                System.out.println("Адреса доставки: " + order.getShippingAddress());
                                order.getCart().displayCart();
                            }
                        }
                        break;
                    case 8:
                        System.out.println("До побачення!");
                        running = false;
                        break;
                    default:
                        System.out.println("Невідома опція. Спробуйте ще раз.");
                }
            }catch (NumberFormatException e) {
                System.out.println("Ви ввели неправильний формат опції. Будь ласка, введіть ціле число.");
            }
        }
    }
}