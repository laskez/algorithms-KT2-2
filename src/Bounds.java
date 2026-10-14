public class Bounds {

    public static int lowerBound(int[] sortedArr, int target) {
        int left = 0, right = sortedArr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (sortedArr[mid] < target) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    public static int upperBound(int[] sortedArr, int target) {
        int left = 0, right = sortedArr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (sortedArr[mid] <= target) left = mid + 1;
            else right = mid;
        }
        return left;
    }
}