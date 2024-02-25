package org.example;

import java.util.List;

public class Product {
    private int id;
    private String name;
    private double price;
    private String description;
    private Category category;
    public Product (int id, String name, double price, String description, Category category){
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    public static Product findProductById(int productId, List<Category> categories) {
        for (Category category : categories) {
            for (Product product : category.getProducts()) {
                if (product.getId() == productId) {
                    return product;
                }
            }
        }
        return null;
    }
    public static void searchProductsByName(String partialName, List<Category> categories) {
        boolean found = false;
        for (Category category : categories) {
            for (Product product : category.getProducts()) {
                if (product.getName().toLowerCase().startsWith(partialName.toLowerCase())) {
                    System.out.println(product.getName() + " (" + category.getName() + "): " + product.getPrice() + " грн");
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("Товари з частковою назвою \"" + partialName + "\" не знайдено.");
        }
    }


    public static void searchProductsByCategory(String categoryName, List<Category> categories) {
        boolean found = false;
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                for (Product product : category.getProducts()) {
                    System.out.println(product.getName() + " (" + category.getName() + "): " + product.getPrice() + " грн");
                }
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Товари у категорії \"" + categoryName + "\" не знайдено.");
        }
    }
}
