/**
 * @param {string[]} strs
 * @return {string}
 */
var longestCommonPrefix = function(strs) {
    let prefix = "", charIndex = 0, i;
    let sameChar = true;
    if (strs.length == 0) return prefix;
    if (strs.length == 1) return strs[0];
    while (sameChar) {
        sameChar = true;
        for (i = 0; i < strs.length - 1; i++) {
            sameChar = sameChar & strs[i][charIndex] == strs[i+1][charIndex];
            if (!sameChar || sameChar && strs[i][charIndex] == undefined) {
                sameChar = false;
                break;
            }
        }
        if (sameChar) {
            prefix += strs[i][charIndex];
            charIndex++;
        }
    }
    return prefix;
};

let strs = ["flower","flow","flight"]
console.time()
console.log(longestCommonPrefix(strs));
console.timeEnd()