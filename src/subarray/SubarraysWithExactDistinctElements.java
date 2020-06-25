package subarray;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class SubarraysWithExactDistinctElements {

    private static void print(Vector<Integer> values) {
        values.stream().forEach(value -> System.out.print(value));
        System.out.println();
    }

    private static void execute(int[] array, int k) {
        Map<Integer, Integer> subarray = new HashMap<>();
        Vector<Integer> values = new Vector<>();
        for (int i = 0; i <= array.length - 2; i++) {
            subarray.clear();
            subarray.put(array[i], 1);
            values.clear();
            values.add(array[i]);
            for (int j = i + 1; j <= array.length - 1; j++) {
                if (subarray.containsKey(array[j])) {
                    subarray.put(array[j], subarray.get(array[j] + 1));
                    values.add(array[j]);
                    print(values);
                } else if (subarray.size() < k) {
                    subarray.put(array[j], 1);
                    values.add(array[j]);
                    print(values);
                }
            }
        }
    }

    public static void main(String... args) {
        int[] array = new int[]{2, 1, 2, 1, 6};
        execute(array, 2);
    }
}