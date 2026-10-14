import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        demo();
        runTests();
    }

    static void demo() {
        int[] original = {5, 2, 8, 1, 9, 3, 2};

        int[] forMerge = Arrays.copyOf(original, original.length);
        int[] forQuick = Arrays.copyOf(original, original.length);

        MergeSort.mergeSort(forMerge, 0, forMerge.length - 1);
        QuickSort.quickSort(forQuick, 0, forQuick.length - 1);

        int[] expected = Arrays.copyOf(original, original.length);
        Arrays.sort(expected);

        System.out.println("Original:    " + Arrays.toString(original));
        System.out.println("MergeSort:   " + Arrays.toString(forMerge));
        System.out.println("QuickSort:   " + Arrays.toString(forQuick));
        System.out.println("Arrays.sort: " + Arrays.toString(expected));
        System.out.println();

        int target = 5;
        System.out.println("binarySearch(" + target + ") = "
                + BinarySearch.binarySearch(forMerge, target));
        System.out.println("lowerBound(" + target + ") = "
                + Bounds.lowerBound(forMerge, target));
        System.out.println("upperBound(" + target + ") = "
                + Bounds.upperBound(forMerge, target));
        System.out.println("Вхождений " + target + ": "
                + (Bounds.upperBound(forMerge, target) - Bounds.lowerBound(forMerge, target)));
        System.out.println();
    }

    static void runTests() {
        Random rnd = new Random(42);

        for (int test = 0; test < 1000; test++) {
            int n = rnd.nextInt(100) + 1;
            int[] original = new int[n];
            for (int i = 0; i < n; i++) original[i] = rnd.nextInt(20) - 10;

            int[] expected = Arrays.copyOf(original, n);
            Arrays.sort(expected);

            // merge sort
            int[] forMerge = Arrays.copyOf(original, n);
            MergeSort.mergeSort(forMerge, 0, n - 1);
            if (!Arrays.equals(forMerge, expected)) {
                System.out.println("MERGE FAIL: " + Arrays.toString(original));
                return;
            }

            // quick sort
            int[] forQuick = Arrays.copyOf(original, n);
            QuickSort.quickSort(forQuick, 0, n - 1);
            if (!Arrays.equals(forQuick, expected)) {
                System.out.println("QUICK FAIL: " + Arrays.toString(original));
                return;
            }

            // binary search & bounds
            for (int t = -12; t <= 12; t++) {
                int myIdx = BinarySearch.binarySearch(expected, t);
                int builtinIdx = Arrays.binarySearch(expected, t);

                boolean myFound = myIdx != -1;
                boolean builtinFound = builtinIdx >= 0;
                if (myFound != builtinFound) {
                    System.out.println("BSEARCH FAIL: target=" + t
                            + " arr=" + Arrays.toString(expected));
                    return;
                }

                int lb = Bounds.lowerBound(expected, t);
                int ub = Bounds.upperBound(expected, t);

                int manualLb = 0;
                while (manualLb < n && expected[manualLb] < t) manualLb++;
                int manualUb = 0;
                while (manualUb < n && expected[manualUb] <= t) manualUb++;

                if (lb != manualLb || ub != manualUb) {
                    System.out.println("BOUNDS FAIL: target=" + t
                            + " arr=" + Arrays.toString(expected)
                            + " | lb=" + lb + " expected=" + manualLb
                            + " | ub=" + ub + " expected=" + manualUb);
                    return;
                }
            }
        }

        System.out.println("ALL TESTS PASSED");
    }
}