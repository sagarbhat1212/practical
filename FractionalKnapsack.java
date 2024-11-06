import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Item {
    int profit, weight;

    public Item(int profit, int weight) {
        this.profit = profit;
        this.weight = weight;
    }
}

class FractionalKnapsack {

    public static double fractionalKnapsack(int w, ArrayList<Item> items) {
        // Sort items based on the profit-to-weight ratio in descending order
        Collections.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                double r1 = (double)o1.profit / o1.weight;
                double r2 = (double)o2.profit / o2.weight;
                return Double.compare(r2, r1); // Sort in descending order
            }
        });

        double finalValue = 0.0;

        for (Item item : items) {
            if (w >= item.weight) {
                // Take the whole item
                finalValue += item.profit;
                w -= item.weight;
            } else {
                // Take fraction of the item
                finalValue += item.profit * ((double) w / item.weight);
                break;
            }
        }

        return finalValue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = scanner.nextInt();
        ArrayList<Item> items = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter profit of item " + (i + 1) + ": ");
            int profit = scanner.nextInt();
            System.out.print("Enter weight of item " + (i + 1) + ": ");
            int weight = scanner.nextInt();
            items.add(new Item(profit, weight));
        }

        System.out.print("Enter capacity of knapsack: ");
        int w = scanner.nextInt();

        double maxProfit = fractionalKnapsack(w, items);
        System.out.println("Maximum profit is: " + maxProfit);

        scanner.close();
    }
}
