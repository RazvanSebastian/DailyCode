package others;

import java.util.Collections;
import java.util.Vector;

/**
 * SOLUTION: multiply step by step the previous result stored in array with the current value from factorial array;
 */
public class BigFactorialProblem {
    /**
     * @param valueDigits
     * @param n
     * @return
     */
    private static Vector<Integer> bigProduct(Vector<Integer> valueDigits, int n) {
        int carry = 0, index = 0;
        Vector<Integer> result = new Vector<>();
        for (index = 0; index < valueDigits.size(); index++) {
            int product = n * valueDigits.get(index) + carry;
            result.add(product % 10);
            carry = product / 10;
        }
        while (carry > 0) {
            result.add(carry % 10);
            carry /= 10;
        }
        return result;
    }

    private static Vector<Integer> computeFactorial(int n) {
        Vector<Integer> factorial = new Vector<>();
        factorial.add(1);
        for (int i = 2; i <= n; i++) {
            factorial = bigProduct(factorial, i);
        }
        Collections.reverse(factorial);
        return factorial;
    }

    public static void main(String... args) {
        int n = 100;
        computeFactorial(n).forEach(i -> System.out.print(i));
        System.out.println();
        n = 10;
        computeFactorial(n).forEach(i -> System.out.print(i));
    }
}