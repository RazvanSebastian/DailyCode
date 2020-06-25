package sortingSearchingHeap;

/**
 * Time complexity O(n^2)
 * Dimension complexity O(1)
 */
public class SelectionSort {

    static void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
        for (int value : array) {
            System.out.print(value + " ");
        }
    }

    public static void main(String... args) {
        int[] array = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        sort(array);
    }
}
