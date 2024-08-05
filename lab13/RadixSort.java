/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        int maxLength = Integer.MIN_VALUE;
        for (String s : asciis) {
            maxLength = Math.max(s.length(), maxLength);
        }

        String[] sorted = asciis.clone();
        for (int i = maxLength; i >= 0; i--) {
            sortHelperLSD(sorted, i);
        }
        return sorted;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        int radix = 256;

        int[] counts = new int[radix + 1];
        for (String s : asciis) {
            int i = s.length() > index ? (int) s.charAt(index) : -1;
            counts[i + 1]++;
        }

        int pos = 0;
        int[] starts = new int[counts.length];
        for (int i = 0; i < counts.length; i++) {
            starts[i] = pos;
            pos += counts[i];
        }

        String[] sorted = new String[asciis.length];
        for (String s : asciis) {
            int idx = s.length() > index ? (int) s.charAt(index) : -1;
            sorted[starts[idx + 1]] = s;
            starts[idx + 1]++;
        }
        System.arraycopy(sorted, 0, asciis, 0, asciis.length);
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String args[]) {
        String[] s = {"cat", "fish", "ant", "puppy", "dog"};
        String[] sorted = sort(s);
        for (String a : sorted) {
            System.out.println(a);
        }
    }
}
