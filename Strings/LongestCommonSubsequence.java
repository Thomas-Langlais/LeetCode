
/**
 * this solution would not work because of the 
 * issues of not using the correct length
 * in the dp.
 * 
 * when we get a character match in the substring
 * we need to look at the length for the substring
 * that did not have the character match.
 * 
 * using a character match also does not work, because it needs to evalute
 * the next best choice, which I implied that the first match was.
 */
class SolutionBruteForce {
    public int longestCommonSubsequence(String text1, String text2) {
        // di[i][j] represents the longest subsequence length at
        // text1.substring(0, i+1) and text2.substring(0, j+1)
        int[][] dp = new int[text1.length()][text2.length()];

        // init the first column
        for (int i = 0; i < text1.length(); i++) {
            // if they match characters
            if (text1.charAt(i) == text2.charAt(0)) {
                dp[i][0] = 1;
            }
            // check if the the subseq length is larger from the substring at 0...i-1
            if (i > 0 && dp[i - 1][0] > dp[i][0]) {
                dp[i][0] = dp[i - 1][0];
            }
        }
        // init the first row
        for (int i = 0; i < text2.length(); i++) {
            // if they match characters
            if (text1.charAt(0) == text2.charAt(i)) {
                dp[0][i] = 1;
            }
            // check if the the subseq length is larger from the substring at 0...i-1
            if (i > 0 && dp[0][i - 1] > dp[0][i]) {
                dp[0][i] = dp[0][i - 1];
            }
        }

        // use the results from the previous DP indexes for the text 1 and 2 subsequences
        // to compare which subsequence we should use
        for (int i = 1; i < dp.length; i++) {
            boolean characterMatch = false;
            for (int j = 1; j < dp[i].length; j++) {
                // leverage the previous result to determine what the
                // starting number will be, since we are using that
                // to enable comparing the characters at i and j and
                // appending that to what the previous longest subsequence
                // was.

                if (dp[i - 1][j] > dp[i][j - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i][j - 1];
                    // ONLY update the length IF the choice was if the subseq length came from
                    // the same index for i, because it 
                    if (!characterMatch && text1.charAt(i) == text2.charAt(j)) {
                        dp[i][j]++;
                        characterMatch = true;
                    }
                }
            }
        }
        // return the result from dp[text1.length()-1][text2.length()-1]
        // as it holds the longest subseqence
        return dp[text1.length()-1][text2.length()-1];
    }
}

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        // di[i][j] represents the longest subsequence length at
        // text1.substring(0, i+1) and text2.substring(0, j+1)
        int[][] dp = new int[text1.length()][text2.length()];

        // init the first column
        for (int i = 0; i < text1.length(); i++) {
            // if they match characters
            if (text1.charAt(i) == text2.charAt(0)) {
                dp[i][0] = 1;
            }
            // check if the the subseq length is larger from the substring at 0...i-1
            if (i > 0 && dp[i - 1][0] > dp[i][0]) {
                dp[i][0] = dp[i - 1][0];
            }
        }
        // init the first row
        for (int i = 0; i < text2.length(); i++) {
            // if they match characters
            if (text1.charAt(0) == text2.charAt(i)) {
                dp[0][i] = 1;
            }
            // check if the the subseq length is larger from the substring at 0...i-1
            if (i > 0 && dp[0][i - 1] > dp[0][i]) {
                dp[0][i] = dp[0][i - 1];
            }
        }

        // use the results from the previous DP indexes for the text 1 and 2 subsequences
        // to compare which subsequence we should use
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                // check if we found a character match
                if (text1.charAt(i) == text2.charAt(j)) {
                    // if so, use the LCS from the i-1,j-1 index to hold the new length
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else if (dp[i - 1][j] > dp[i][j - 1]) {
                    // if there was no character match, use the
                    // LCS length from the column before or row above
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        // return the result from dp[text1.length()-1][text2.length()-1]
        // as it holds the longest subseqence
        return dp[text1.length()-1][text2.length()-1];
    }
}

public class LongestCommonSubsequence {
    
    public static void main(String[] args) {
        // for (int i = 0; i <= args.length - 2; i += 2) {
        //     System.out.printf("Longest subsequence for '%s' and '%s' is %d", args[i], args[i + 1], new SolutionBruteForce().longestCommonSubsequence(args[i], args[i + 1]));
        // }
        String text1 = "bsbininm";
        String text2 = "jmjkbkjkv";
        System.out.printf("Longest subsequence for '%s' and '%s' is %d%n", text1, text2, new Solution().longestCommonSubsequence(text1, text2));
        text1 = "abcba";
        text2 = "abcbcba";
        System.out.printf("Longest subsequence for '%s' and '%s' is %d%n", text1, text2, new Solution().longestCommonSubsequence(text1, text2));
        text1 = "pmjghexybyrgzczy";
        text2 = "hafcdqbgncrcbihkd";
        System.out.printf("Longest subsequence for '%s' and '%s' is %d%n", text1, text2, new Solution().longestCommonSubsequence(text1, text2));
    }
    // bsbininm
    // jmjkbkjkv
}
