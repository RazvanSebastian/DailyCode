package sortingSearchingHeap;

/**
 * Find the K'th smallest/largest element in array
 * <p>
 * SOLUTION: Using the partitioning approach; placing the pivot element into the right place and checking if it is the K'th smallest/largest element; if it's not continue to the left or to the right
 * <p>
 * CONCLUSION: With this approach we don't have to sort all array.
 */
public class FindKthElement {

    static int partitioning(int[] array, int left, int right) {
        int i = left - 1;
        int pivot = array[right];
        for (int j = left; j <= right - 1; j++) {
            if (array[j] <= pivot) {
                i++;
                int aux = array[i];
                array[i] = array[j];
                array[j] = aux;
            }
        }
        int aux = array[i + 1];
        array[i + 1] = array[right];
        array[right] = aux;

        return (i + 1);
    }

    static int largestKthElement(int[] array, int k) {
        int left = 0;
        int right = array.length - 1;
        int n = array.length - 1;
        int pivotIndex = partitioning(array, left, right);
        while (pivotIndex != n - k + 1) {
            if (pivotIndex < right - k + 1) {
                /**
                 * partitioning :  [___]pivot[___K'th-element___]
                 */
                left = pivotIndex + 1;
            } else {
                /**
                 * partitioning :  [__K'th-element__]pivot[______]
                 */
                right = pivotIndex - 1;
            }
            pivotIndex = partitioning(array, left, right);
        }
        return array[pivotIndex];
    }

    static int smallestKthElement(int[] array, int k) {
        int left = 0;
        int right = array.length - 1;
        int pivotIndex = partitioning(array, left, right);
        while (pivotIndex != k - 1) {
            if (k - 1 < pivotIndex) {
                /**
                 * partitioning :  [__K'th-element__]pivot[______]
                 */
                right = pivotIndex - 1;
            } else {
                /**
                 * partitioning :  [___]pivot[___K'th-element___]
                 */
                left = pivotIndex + 1;
            }
            pivotIndex = partitioning(array, left, right);
        }
        return array[pivotIndex];
    }

    public static void main(String... args) {
        int[] array = new int[]{2, 1, 3, 4, 6, 7, 5};
        int kLargestElement = 2;
        System.out.format("The %d'th largest element is %d \n", kLargestElement, largestKthElement(array, kLargestElement));

        array = new int[]{2, 1, 3, 4, 6, 7, 5};
        int kSmallestElement = 3;
        System.out.format("The %d'th smallest element is %d", kSmallestElement, smallestKthElement(array, kSmallestElement));
    }
}
