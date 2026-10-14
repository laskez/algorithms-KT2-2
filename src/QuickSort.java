public class QuickSort {

    public static void quickSort(int[] arr, int low, int high) {
        if (low >= high) return;
        int pivotIndex = partition(arr, low, high);
        quickSort(arr, low, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, high);
    }

    public static int partition(int[] arr, int low, int high) {
        int mid = low + (high - low) / 2;
        int pivot = medianOfThree(arr, low, mid, high);

        int pivotPos;
        if (pivot == arr[low]) pivotPos = low;
        else if (pivot == arr[mid]) pivotPos = mid;
        else pivotPos = high;

        swap(arr, pivotPos, high);

        int i = low;
        for (int j = low; j < high; j++) {
            if (arr[j] <= arr[high]) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, high);
        return i;
    }

    private static int medianOfThree(int[] arr, int a, int b, int c) {
        int x = arr[a], y = arr[b], z = arr[c];
        if ((x <= y && y <= z) || (z <= y && y <= x)) return y;
        if ((y <= x && x <= z) || (z <= x && x <= y)) return x;
        return z;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}