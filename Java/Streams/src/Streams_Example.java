import java.util.Arrays;
import java.util.List;

class Transaction {
    private String category;

    private int amount;

    public Transaction(String category, int amount) {
        this.amount = amount;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
    public int getAmount(){
        return amount;
    }
}

public class Streams_Example {

    public static void main(String[] args) {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("Grocery",2500),
                new Transaction("Entertainment",1500),
                new Transaction("Grocery",500),
                new Transaction("Utilities",500),
                new Transaction("Utilities",1200),
                new Transaction("Entertainment",1200)

        );

//        case 1 calculate the total amount spent on groceries
        int amount = transactions.stream()
                .filter(c-> c.getCategory().equals("Grocery"))
                .mapToInt(a -> a.getAmount()).sum();

        System.out.println(amount);

//        use case 2 find the average expense across all groceries
        Double averageExpense = transactions.stream()
                .filter(c -> c.getCategory().equals("Grocery"))
                .mapToDouble( a->a.getAmount()).average().orElse(0.0);
        System.out.println("Average Expense"+averageExpense);

    }
}
