import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

public class RunRBT {
    public static void main(String[] args) {
        // Test 1
        LinkedList<Integer> l1 = new LinkedList<>();
        for (int i = 100; i > 0; i--) {
            l1.add(i);
        }
        // Test 2
        buildAndPrintTree(l1);
        LinkedList<Integer> l2 = new LinkedList<>();
        for (int i = 500; i > 0; i -= 2) {
            l2.add(i);
        }
        buildAndPrintTree(l2);
        LinkedList<Integer> l3 = new LinkedList<>();
        l3.add(10);
        l3.add(5);
        l3.add(15);
        l3.add(20);
        buildAndPrintTree(l3);
    }

    public static void buildAndPrintTree(List<Integer> inputs) {
        RBT tree = new RBT();
        for (int i : inputs) {
            tree.insert(i);
        }
        List<Integer> output = tree.serializeTree();
        System.out.println(output);
        System.out.println("Max tree height: " + tree.maxHeight());
        System.out.println("Maintained all values: " + (output.containsAll(inputs) && inputs.containsAll(output)));
        List<Integer> tmp = new LinkedList<>(output);
        Collections.sort(tmp);
        System.out.println("Output is sorted: " + (tmp.equals(output)));
    }


}
