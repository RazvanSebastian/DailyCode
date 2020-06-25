package dynamicProgramming;

/**
 * Kadane's algorithm
 * <p>
 * Choose: max(A[i], A[i]+max[i-1])
 * <p>
 * IDEA: check it you have a greater sum by adding the current element to the previous best sum or you can start a new sum with the current element
 */
public class MaxContiguousSubArray {

    static void findMaxSum(int[] array) {
        int localMax = array[0], globalMax = 0;
        for (int i = 1; i < array.length; i++) {
            localMax = Math.max(array[i], localMax + array[i]);
            if (localMax > globalMax) {
                globalMax = localMax;
            }
        }
        System.out.format("The max sum of a contiguous sub array is %d", globalMax);
    }

    public static void main(String... args) {
        int[] array = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        findMaxSum(array);
    }
}
