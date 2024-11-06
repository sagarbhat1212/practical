import java.util.Arrays;
import java.util.Random;

public class QuickSortComparison {

    private static Random random = new Random();

    // Deterministic partition method
    public static int deterministicPartition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    // Randomized partition method
    public static int randomizedPartition(int[] arr, int low, int high) {
        int pivotIndex = random.nextInt(high - low + 1) + low;
        swap(arr, pivotIndex, high);
        return deterministicPartition(arr, low, high);
    }

    // Quick Sort method
    public static void quickSort(int[] arr, int low, int high, boolean useRandomizedPivot) {
        if (low < high) {
            int pivotIndex;
            if (useRandomizedPivot) {
                pivotIndex = randomizedPartition(arr, low, high);
            } else {
                pivotIndex = deterministicPartition(arr, low, high);
            }
            quickSort(arr, low, pivotIndex - 1, useRandomizedPivot);
            quickSort(arr, pivotIndex + 1, high, useRandomizedPivot);
        }
    }

    // Swap helper method
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arrSizes = {100, 1000, 10000, 100000};

        for (int size : arrSizes) {
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = random.nextInt(1000) + 1;
            }
            Arrays.sort(arr);
            for (int i = 0; i < size / 2; i++) { // Reverse the array for a worst-case scenario
                swap(arr, i, size - i - 1);
            }

            int[] arrCopy1 = Arrays.copyOf(arr, arr.length);
            int[] arrCopy2 = Arrays.copyOf(arr, arr.length);

            long startTime = System.nanoTime();
            quickSort(arrCopy1, 0, arrCopy1.length - 1, false);
            long deterministicTime = System.nanoTime() - startTime;

            startTime = System.nanoTime();
            quickSort(arrCopy2, 0, arrCopy2.length - 1, true);
            long randomizedTime = System.nanoTime() - startTime;

            System.out.println("Array size: " + size);
            System.out.printf("Deterministic Quick Sort time: %.6f seconds\n", deterministicTime / 1e9);
            System.out.printf("Randomized Quick Sort time: %.6f seconds\n", randomizedTime / 1e9);
            System.out.println("-".repeat(40));
        }
    }
}
