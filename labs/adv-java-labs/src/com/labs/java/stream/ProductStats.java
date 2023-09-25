package com.labs.java.stream;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductStats {
	public static void main(String[] args) {
        Product product1 = new Product(1, "Galaxy S13", "Mobiles", "Samsung", 99999.0);
        Product product2 = new Product(2, "iPhone 15", "Mobiles", "Apple", 80000.0);
        Product product3 = new Product(3, "Thinkpad E490", "Laptops", "Lenevo", 65000.0);
        Product product4 = new Product(4, "Pavilion", "Laptops", "HP", 75000.0);
        Product product5 = new Product(5, "OLED 55", "SmartTV", "LG", 150000.0);
        Product product6 = new Product(6, "OLED K55", "SmartTV", "Sony", 145000.0);

        List<Product> products = List.of(product1, product2, product3, product4, product5, product6);

        // 1. No of products whose price more than 75k
        long count = products.stream()
                .filter(p -> p.getPrice() > 75000.0)
                .count();
        System.out.println("No of products whose price more than 75k - " + count);

        // 2. Product category wise count
        Map<String, Long> countByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
        System.out.println("Product category wise count - " + countByCategory);

        // 3. List of Product IDs of Laptops
        List<Integer> productIds = products.stream()
                .filter(p -> p.getCategory() == "Laptops")
                .map(p -> p.getId())
                .collect(Collectors.toList());
        System.out.println("List of Product IDs of Laptops - " + productIds);

        // 4. Product Stats - Min, Max, Total and Average price
        DoubleSummaryStatistics stats = products.stream()
                .map(p -> p.getPrice())
                .mapToDouble(x -> x)
                .summaryStatistics();

        System.out.println("Minimum Product Price - " + stats.getMin());
        System.out.println("Maximum Product Price - " + stats.getMax());
        System.out.println("Total Product Price - " + stats.getSum());
        System.out.println("Avg Product Price - " + stats.getAverage());

    }
}
