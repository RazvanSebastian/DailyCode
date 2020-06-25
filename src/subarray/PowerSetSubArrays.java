package subarray;

public class PowerSetSubArrays {

    static void printPowerSet(char[] set) {
        int subsetsNo = 1 << set.length;
        System.out.format("Number os subsets = %d \n", subsetsNo);

        for (int i = 0; i < subsetsNo; i++) {
            int m = 1;
            System.out.print("\n {");
            for (int j = 0; j < set.length; j++) {
                if ((i & m) > 0) {
                    System.out.print(set[j] + " ");
                }
                m = m << 1;
            }
            System.out.print("}");
        }
    }

    public static void main(String[] args) {
        char[] set = {'a', 'b', 'c'};
        printPowerSet(set);
    }
}
