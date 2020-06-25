package dynamicProgramming;

import java.util.*;

/**
 * Problem: Having N types of coins and a value V, the request is to find the minimum number of coins to build the V value
 * <p>
 * Solution: Create at every step the minimum number of coins to build the sum[i], where i=0:V
 */
public class MinimCoinsChange {

    public static Map<Integer, List<Integer>> getInitializedHashMap(int value) {
        Map<Integer, List<Integer>> previousCoins = new HashMap<>();
        for (int i = 0; i <= value; i++) {
            previousCoins.put(i, new ArrayList<>());
        }
        return previousCoins;
    }

    public static Map<Integer, List<Integer>> calculateNumberOfCoins(int[] coins, int value) {
        // 0...value => size = value + 1
        int[] numberOfCoins = new int[value + 1];
        Arrays.fill(numberOfCoins, value + 1);
        numberOfCoins[0] = 0;

        // remember the coins used to build previous changes
        Map<Integer, List<Integer>> previousCoins = getInitializedHashMap(value);

        for (int i = 1; i <= value; i++) {
            int rememberCoin = 0;
            int rememberPreviousChange = 0;
            for (int j = 0; j < coins.length; j++) {
                // Try to build the current sum with previous changes
                int previousMinimChange = i - coins[j];
                if (previousMinimChange < 0 || numberOfCoins[previousMinimChange] == value + 1 || numberOfCoins[previousMinimChange] + 1 >= numberOfCoins[i]) {
                    continue;
                }
                // Remember the best coin and the best previous change to build the current change
                rememberCoin = coins[j];
                rememberPreviousChange = previousMinimChange;
                numberOfCoins[i] = numberOfCoins[previousMinimChange] + 1;
            }
            // If we have a valid choice remember the previous best change + coin
            if (rememberCoin != 0) {
                previousCoins.get(i).addAll(previousCoins.get(rememberPreviousChange));
                previousCoins.get(i).add(rememberCoin);
            }
        }
        return previousCoins;
    }

    public static void main(String... args) {
        int[] coins = new int[]{2, 4};
        int value = 9;

        Map<Integer, List<Integer>> result = calculateNumberOfCoins(coins, value);
        result.forEach((k, v) -> {
            System.out.print(k + " = [");
            v.forEach(c -> System.out.print(c + " "));
            System.out.print("]\n");
        });

        if (result.get(9).isEmpty()) {
            System.out.println("Could not compute change!");
        } else {
            System.out.println("");
        }

    }
}
