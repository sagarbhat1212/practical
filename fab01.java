import java.util.Scanner;

public class fab01 {

    // Iterative Fibonacci function with step count
    public static int[] fibonacciIter(int n) {
        if (n < 0) {
            return new int[]{-1, 1};  // Error condition
        }
        if (n == 0 || n == 1) {
            return new int[]{n, 1};  // Base case with steps counted as 1
        }

        int steps = 0;
        int a = 0, b = 1, c = 0;

        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
            steps++;
        }
        return new int[]{c, steps + 1}; // Return the result and the number of steps
    }

    // Recursive Fibonacci function with step count
    public static int[] fibonacciRecur(int n) {
        if (n < 0) {
            return new int[]{-1, 1}; // Error condition
        }
        if (n == 0 || n == 1) {
            return new int[]{n, 1}; // Base case with steps counted as 1
        }

        int[] fib1 = fibonacciRecur(n - 1);
        int[] fib2 = fibonacciRecur(n - 2);
        int fibValue = fib1[0] + fib2[0];
        int steps = fib1[1] + fib2[1] + 1;

        return new int[]{fibValue, steps}; // Return the result and the number of steps
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = scanner.nextInt();

        int[] iterResult = fibonacciIter(n);
        System.out.println("Iterative: " + iterResult[0]);
        System.out.println("Steps: " + iterResult[1]);

        int[] recurResult = fibonacciRecur(n);
        System.out.println("Recursive: " + recurResult[0]);
        System.out.println("Steps: " + recurResult[1]);

        scanner.close();
    }
}
