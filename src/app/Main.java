package app;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Laptop", "Electronics", 1200.0),
                new Product("Coffee Maker", "Appliances", 80.0),
                new Product("Headphones", "Electronics", 150.0),
                new Product("Blender", "Appliances", 50.0)
        );

       // Згруповані продукти за їхніми категоріями
        Map<String, List<Product>> mapA = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));

       mapA.forEach((key, value) -> {
           System.out.println("\nCategory - " + key + ":");
           value.forEach(e -> System.out.println(e.getName()) );
       });

        //Ceредня ціна для кожної категорії
        Map<String, Double> mapB = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.averagingDouble(Product::getPrice)));

        mapB.forEach((key, value) -> {
            System.out.println("\nCategory - " + key + " average price is " + value);

        });

        //Категорія з найвищою середньою ціною
        Optional<Map.Entry<String, Double>> maxResult = mapB.entrySet().stream()
                .max(Map.Entry.comparingByValue());
        double maxValue = maxResult.get().getValue();
        String keyValue = maxResult.get().getKey();

        System.out.println("\nCategory with the highest average Price is " + keyValue +  " and the price is " + maxValue);

    }
}
