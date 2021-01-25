import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        for (int i = 0; i < args.length; i++) {
            System.out.println(sol.longestPalindrome(args[i]));
            for (Map.Entry<List<Integer>, String> entry : sol.Memoize.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue().toString());
            }
            sol.Memoize.clear();
        }
    }
    
    private Map<List<Integer>, String> Memoize = new HashMap<List<Integer>, String>();
    private String String = null;

    public String longestPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        if (s.length() <= 1) return s;
        this.String = s;
        return this.searchLCSPal(left, right);
    }

    public String searchLCSPal(int left, int right) {
        // we use memoization to check if this has an entry
        List<Integer> key = new ArrayList<Integer>(2);
        key.add(left);
        key.add(right);
        // check for exit conditions
        // left and right cross each other or are equal
        if (left > right || right < left) {
            return "";
        }
        // if not, we compute its result
        if (!this.Memoize.containsKey(key)) {
            System.out.println("Searching " + this.String.substring(0, left) + "'" + this.String.substring(left, right+1) + "'" + this.String.substring(right+1, this.String.length()));
            // assume the substring is a palindrome until proven other wise
            // it uses the endIndex as a countable int - strange but ok (e.g. why i use right + 1)
            String result = this.String.substring(left, right + 1);
            // check if the left and right substring is a palindrome
            // we don't need to compare the size because we know that anything
            // below in the rescursed calls will have smaller ranges
            if (!this.isPalindrome(result)) {
                // recurse into the 2 options of incrementing left
                // or decrementing right and pick witch one to select
                if (this.searchLCSPal(left + 1, right).length() > this.searchLCSPal(left, right - 1).length()) {
                    result = this.searchLCSPal(left + 1, right);
                } else {
                    result = this.searchLCSPal(left, right - 1);
                }
            }
            // cache the value
            this.Memoize.put(key, result);
        }
        // return the cached entry
        return this.Memoize.get(key);
    }

    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        // check if the string is a length of one
        // because that is a palindrome
        if (s.length() == 1) return true;
        // iterate over the string with the left and aright index and
        // check if it's a palindrome
        while (left <= right)
            if (s.charAt(left++) != s.charAt(right--)) return false;
        return true;
    }
}

// we are given the max size 1000
// only english letters and digits