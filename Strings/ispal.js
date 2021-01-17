/**
 * @param {string} s
 * @return {boolean}
 */
var isPalindrome = function(s) {
    s = s.toLowerCase();
    let left = 0, right = s.length - 1;
    let reg = /[a-zA-Z0-9]/;
    while (left <= right) {
        if (!reg.test(s[left])) {
            left++;
            continue;
        }
        if (!reg.test(s[right])) {
            right--;
            continue;
        }
        if (s[left] != s[right]) {
            return false;
        } else {
            left++;
            right--;
        }
    }
    return true;
};

let input = "A man, a plan, a canal: Panama";
console.log(isPalindrome(input));