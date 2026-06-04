import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams_ExampleData {

    public static void main(String[] args) {
        //Immutable list, allows null
        var immutableList = Stream.of("red","green",null).toList();
//        immutableList.add("Yellow");
        System.out.println(immutableList);

        //Modifiable List, allows null
        var modifiableList = Stream.of("red","green",null).collect(Collectors.toList());
        modifiableList.add("brown");
        modifiableList.set(2,"Yellow");
        System.out.println(modifiableList);

        record Product(String name,String category, int price){ };
        Stream<Product> products = Stream.of(new Product("Laptop","Electronics",1000),
        new Product("TV","Electronics",3000),
        new Product("Sofa","Furniture",1000),
        new Product("Table","Furniture",1000),
        new Product("Lamp","Electronics",1000));


        //Grouping products by category

//        Map<String, List<Product>> groupProductsByCategory = products.collect(Collectors
//                .groupingBy(Product::category));
//        System.out.println(groupProductsByCategory);

        //Aggregation : calculate the total price
//        of products by category
//        Map<String, Integer> sumProductsByCategory = products.collect(Collectors
//                .groupingBy(Product::category,Collectors.summingInt(Product::price)));
//
//        System.out.println(sumProductsByCategory);

        //Aggregation :  counting and average of products by category
//        Map<String, Double> avgProductsByCategory = products.collect(Collectors
//                .groupingBy(Product::category,Collectors.averagingInt(Product::price)));
//
//        System.out.println(avgProductsByCategory);

        //Filtering : Products with price greater than 50
//        var filteredProducts = products.filter(product -> product.price>50).toList();
//        System.out.println(filteredProducts);
//        var extractProductCategories = products.map(Product::category).distinct().toList();
//        System.out.println(extractProductCategories);

        //Partitioning : Separate products into expensive and cheap

//        var partitionedProducts = products.collect(Collectors.partitioningBy(product -> product.price > 1000));
//        System.out.println(partitionedProducts);

        //Using SummaryStatistics for product prices
        //sum,average,min,max,count(5 metrics)
        Map<String, IntSummaryStatistics> summaryStatisticsByCategory = products.collect(Collectors
               .groupingBy(Product::category,Collectors.summarizingInt(Product::price)));
      System.out.println(summaryStatisticsByCategory);













    }
}
