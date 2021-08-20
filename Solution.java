import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    /**
     * Return smallest possible positive integer not in numbers
     * e.g.
     *     [1,2,4] returns 3
     *     [12,-7,5] returns 1
     *     [1,...*1mil] returns 2
     * 
     * @param numbers
     * @return
     */
    public int asd(int[] numbers) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        result = 1;
        // remove the duplicates from the soruce array
        for (int i = 0; i < numbers.length; i++) {
            if (!map.containsKey(numbers[i])) {
                map.put(numbers[i], numbers[i]);
            }
        }

        while (true) {
            if (!map.containsKey(result)) return result;
            result++;
        }

        return result - 1;
    }
}