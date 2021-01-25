import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This solution works, but it becomes incredibly inefficient
 * when you have larger strings due to recursion on all the combinations of substrings O(n^2) and palindrome checks of O(n)
 * 
 * The next solution will solve it to make palindrome checks == O(1)
 */
class SolutionV1 {
    
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

/**
 * SolutionV2 
 * Got way too focused on using recursion to sove teh problem...
 * Try to think of the ways to solve the problen.
 * 
 * in this case, I should have looked to see the characteristics of a palindrome.
 * on the polar ends moving inward, the characters must match. therefore if we can determine
 * the center points of these strings. we can use that to look for the longest palindrome.
 * 
 * I also could have used DP to have a NxN matrix and start from each character index M[i][i] and work outwards
 * to solve the problem
 */
class SolutionV2 {

    // the memoized map for palindrome checks
    private Map<String, Boolean> Memoize = new HashMap<String, Boolean>();

    public String longestPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        if (s.length() <= 1) return s;
        return this.findPalindrome(s, left, right);
    }

    private String findPalindrome(String s, int left, int right) {
        // check for exit conditions
        // left and right cross each other or are equal
        if (left > right || right < left) {
            return "";
        }
        // we were memoizing the wrong checks
        // we want to memoize the palindrome checks because
        // the substring combinations are unavoidable
        String sub = s.substring(left, right + 1);
        if (this.isPalindrome(sub)) {
            // if it's a palindrome, them we want to check if the characters left and right of the 
            // substring are the same
            // e.g. s = "abbba", left = 2, right = 2 --> check if char at 1 and 3 are the same
            return sub;
        } else {
            // if it's not a palindrome, check the substring combinations inside s
            // recurse into the 2 options of incrementing left
            // or decrementing right and pick witch one to select
            String leftResult = this.findPalindrome(s, left + 1, right);
            String rightResult = this.findPalindrome(s, left, right - 1);
            if (leftResult.length() > rightResult.length()) {
                return leftResult;
            } else {
                return rightResult;
            }
        }
    }

    private boolean isPalindrome(String s) {
        // check if it's been done before, if not, recurse by removing the left/right most characters off
        if (!this.Memoize.containsKey(s)) {
            if (s.length() < 2) {
                this.Memoize.put(s, true);
            } else {
                String sub = s.substring(1, s.length() - 1);
                // check the recursed results
                this.Memoize.put(s, this.isPalindrome(sub) && s.charAt(0) == s.charAt(s.length() - 1));
            }
        }
        return this.Memoize.get(s);
    }
}

class SolutionV3 {

    private boolean[][] MemoizedPalindromes = null;

    public String longestPalindrome(String s) {
        int n = s.length();
        // edge case
        if (s.length() <= 1) return s;

        MemoizedPalindromes = new boolean[n][n];
        // init the index for 1 character palindromes. aka the diagonal
        for (int i = 0; i < n; ++i) {
            MemoizedPalindromes[i][i] = true;
            // init the line below the diagonal if it's not out of range
            if (i < n - 1) MemoizedPalindromes[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
        }

        int highestLength = 0;
        int maxStart = 0, maxEnd = 0;
        // look at the substring indexes next to i and move outward
        // for every substring center point
        // e.g i = 4 look at in order: 3 and 5, 2 and 6, etc
        for (int start = n - 3; start >= 0; --start) {
            for (int end = start + 2; end < n; ++end) {
                MemoizedPalindromes[start][end] = MemoizedPalindromes[start + 1][end - 1] && s.charAt(start) == s.charAt(end);
                if (MemoizedPalindromes[start][end] && end - start + 1 > highestLength) {
                    highestLength = end - start + 1;
                    maxStart = start;
                    maxEnd = end;
                }
            }
        }
        
        // return the longest palindromic substring
        return s.substring(maxStart, maxEnd + 1);
    }
}

/**
 * The most optimal solution.
 * it seams that the DP solution works, but still does not compare to the 
 * looping over the center points. 
 */
class SolutionFinal {

    int maxLen = 0;
    int start=0;
    
    public String longestPalindrome(String s) {
        // basic loop through center points and check if its a palindrome
        if(s.length() < 2) return s;

        for(int i = 0; i < s.length(); i++){
            isValidPalindrome(s, i, i);
            isValidPalindrome(s, i, i + 1);
        }
        return s.substring(start, maxLen + start);
    }
    
    public void isValidPalindrome(String s, int left, int right) {
        // loop through the left and right indexes until we reach
        // the start or end of the string or the substring is no longer palindromic
        while(left >= 0 && right<s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        // check if we found a longer palindrome
        if(maxLen < right - left - 1){
            maxLen = right - left - 1;
            start = left + 1;
        }
    }
}
/**
 * LongestPalindromeSubstring
 */
public class LongestPalindromeSubstring {

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            long start, end;
            String result;

            start = System.currentTimeMillis();
            result = new SolutionV1().longestPalindrome(args[i]);
            end = System.currentTimeMillis();
            System.out.printf("V1 time = %d ms%n", (end - start));
            System.out.println(result);

            // does not work
            // start = System.currentTimeMillis();
            // result = new SolutionV2().longestPalindrome(args[i]);
            // end = System.currentTimeMillis();
            // System.out.printf("V1 time = %d ms%n"+System.lineSeparator(), (end - start));
            // System.out.println(result);
            
            start = System.currentTimeMillis();
            result = new SolutionV3().longestPalindrome(args[i]);
            end = System.currentTimeMillis();
            System.out.printf("V3 time = %d ms%n", (end - start));
            System.out.println(result);

            start = System.currentTimeMillis();
            result = new SolutionFinal().longestPalindrome(args[i]);
            end = System.currentTimeMillis();
            System.out.printf("Optimal Sol time = %d ms%n", (end - start));
            System.out.println(result);
        }
    }
}

// we are given the max size 1000
// only english letters and digits