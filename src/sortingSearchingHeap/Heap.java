package sortingSearchingHeap;

import java.util.Arrays;
import java.util.Vector;

/**
 * It is not the most optimized code, but it is much more ease to follow :)
 *
 * @Note: I have started with the indexing from 1 instead from 0; I have used into {@link #swap} method, the first element :)
 *
 * @Complexity:
 * Time : n * log(n)
 * Dimension : 2 * n since I am using a second vector to store the sorted values
 */
public class Heap {

    public static void insertIntoHeap(Vector<Integer> heap, int newValue) {
        heap.add(newValue);
        int newValueIndex = heap.size() - 1;
        int parentIndex = newValueIndex / 2;
        while (parentIndex >= 1 && heap.get(parentIndex) < newValue) {
            //swap between parent and child
            swap(heap, parentIndex, newValueIndex);
            //advance
            newValueIndex = parentIndex;
            parentIndex = parentIndex / 2;
        }
    }

    public static int deleteFromHeap(Vector<Integer> heap) {
        //move the last element on the root position and remember the last element
        int firstElement = heap.get(1);
        heap.setElementAt(heap.lastElement(), 1);
        heap.remove(heap.size() - 1);
        //rebuild the heap
        int nodeIndex = 1;
        int leftChildIndex = nodeIndex * 2;
        int rightChildIndex = nodeIndex * 2 + 1;
        while (true) {
            // the node is a leaf
            if (leftChildIndex > heap.size() - 1) {
                return firstElement;
            }
            // the node has only leftChild
            else if (rightChildIndex > heap.size() - 1) {
                if (heap.get(nodeIndex) < heap.get(leftChildIndex)) {
                    swap(heap, leftChildIndex, nodeIndex);
                }
                return firstElement;
            }
            // the node parent has both children
            else {
                // the node is greater than both children
                if (heap.get(nodeIndex) > heap.get(leftChildIndex) && heap.get(nodeIndex) > heap.get(rightChildIndex)) {
                    return firstElement;
                }
                // compare to check with which children to swap (swap with max between left and right)
                if (heap.get(leftChildIndex) > heap.get(rightChildIndex)) {
                    swap(heap, leftChildIndex, nodeIndex);
                    nodeIndex = leftChildIndex;
                } else {
                    swap(heap, rightChildIndex, nodeIndex);
                    nodeIndex = rightChildIndex;
                }
                leftChildIndex = nodeIndex * 2;
                rightChildIndex = nodeIndex * 2 + 1;
            }
        }
    }

    private static void swap(Vector<Integer> heap, int index1, int index2) {
        heap.set(0, heap.get(index1));
        heap.set(index1, heap.get(index2));
        heap.set(index2, heap.get(0));
        heap.set(0, 0);
    }

    public static void main(String... args) {
        //start from index 1
        Vector<Integer> heap = new Vector<>(Arrays.asList(0));
        insertIntoHeap(heap, 10);
        insertIntoHeap(heap, 20);
        insertIntoHeap(heap, 15);
        insertIntoHeap(heap, 30);
        insertIntoHeap(heap, 40);
        System.out.println("Max heap");
        heap.forEach(value -> System.out.format("%d ", value));

        System.out.println("\nHeap sort by removing from heap");
        Vector<Integer> sortedFromHeap = new Vector<>();
        while (heap.size() > 1) {
            sortedFromHeap.add(deleteFromHeap(heap));
        }
        sortedFromHeap.forEach(value -> System.out.format("%d ", value));
    }

}
