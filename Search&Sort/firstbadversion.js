/**
 * Definition for isBadVersion()
 * 
 * @param {integer} version number
 * @return {boolean} whether the version is bad
 * isBadVersion = function(version) {
 *     ...
 * };
 */

/**
 * @param {function} isBadVersion()
 * @return {function}
 */
var solution = function(isBadVersion) {
    /**
     * @param {integer} n Total versions
     * @return {integer} The first bad version
     */
    return function(n) {
        let left = 1, right = n, midpoint;
        while (left < right) {
            // check the API version at the midpoint
            // use the bitwise to shift the decimal out
            midpoint = ((right + left) / 2) >> 0;
            if (isBadVersion(midpoint)) {
                // if the midpoint version is bad, the search scope is left to midpoint
                right = midpoint;
            } else {
                // if it's not a bad version, the search scope is midpoint to right
                left = midpoint+1;
            }
        }
        return left;
    };
};

// I viewed the question differently. Here the left
// variable always tracks the last known good version.
// and the right variable always refers to a bad version.
// this is why when the while statement ends, we know that
// the first bad version is the right variable.
// The way I implemented it was always having the unknown versions on left.
// e.g, if i know that the midpoint is a good version, then i'll make it so that the
// left variable is the version after the midpoint, then once it's exits the loop, we know
// that the last known bad version is the left 
var solutionOptimal = function(isBadVersion) {
    return function(n) {
        let left = 0, right = n, mid;
        while (right - left != 1) {
            mid = ((right + left) / 2) >> 0;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }
}
// 1,2,3,4,5
let n = 5, bad = 0, calls = 0;
for (let bad = 1; bad <= n; bad++) {
    console.log(`versions: ${n}, bad version: ${bad}`);
    console.log(`calculated bad version: ${solution((v) => {
        calls++;
        return v >= bad;
    })(n)}, number of calls: ${calls}`);
    console.log();
    calls = 0;
}

console.log("optimal solution from web");
for (let bad = 1; bad <= n; bad++) {
    console.log(`versions: ${n}, bad version: ${bad}`);
    console.log(`calculated bad version: ${solutionOptimal((v) => {
        calls++;
        return v >= bad;
    })(n)}, number of calls: ${calls}`);
    console.log();
    calls = 0;
}