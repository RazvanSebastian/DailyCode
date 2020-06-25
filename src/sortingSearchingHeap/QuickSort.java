package sortingSearchingHeap;

import java.util.Stack;

public class QuickSort {

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

    static void quickSort(int[] array, int left, int right) {
        Stack<Integer> stack = new Stack<>();
        stack.push(left);
        stack.push(right);
        while (!stack.empty()) {
            right = stack.pop();
            left = stack.pop();
            int pivotIndex = partitioning(array, left, right);
            //if we have elements to the left of pivot add a new partition into the stack
            if (left < pivotIndex - 1) {
                stack.push(left);
                stack.push(pivotIndex - 1);
            }
            //if we have elements to the right of pivot add a new partition into the stack
            if (pivotIndex + 1 < right) {
                stack.push(pivotIndex + 1);
                stack.push(right);
            }
        }
    }

    public static void main(String... args) {
        int[] array = new int[]{2, 1, 3, 4, 6, 7, 5};
        quickSort(array, 0, array.length - 1);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }
    }

}