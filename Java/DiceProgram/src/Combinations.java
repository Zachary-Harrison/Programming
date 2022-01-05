import java.util.Arrays;
import java.util.LinkedList;

public class Combinations extends LinkedList<Integer>
{
    public boolean keepGoing = true;
    private final int length;
    private final int base;
    private final int[] head;

    public Combinations(int length, int base)
    {
        this.length = length;
        this.base = base;
        this.head = new int[length];
        for (int i = 0; i < length; i++) {
            head[i] = base;
        }
    }

    public void downOne()
    {
        for (int i = 0; i < this.length; i++) {
            if (head[i] > 1) {
                head[i]--;
                return;
            } else if (head[i] == 1) {
                head[i] = base;
                if ( (i+1) < length && head[i+1] > 1) {
                    head[i+1]--;
                    return;
                }
            }
        }
        keepGoing = false;
    }

    public int[] getCombinations(int dropLow, int dropHigh)
    {
        int[] arr = new int[length];
        if (length >= 0) System.arraycopy(head, 0, arr, 0, length);
        Arrays.sort(arr);
        return Arrays.copyOfRange(arr, dropLow, length-dropHigh);
    }

    public void printCombinations() {
        System.out.println(Arrays.toString(head));
    }

    public void printDropCombinations(int dropLow, int dropHigh) {
        System.out.println(Arrays.toString(getCombinations(dropLow, dropHigh)));
    }
}
