function executionTime(func) {
    return function() {
        console.time();
        let lkpOccurancess = func.apply(this, arguments);
        console.timeEnd();
        return lkpOccurancess;
    }
}

/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number[]}
 */
var old = function(small, large) {
    let swap;
    let lkpOccurances = []
    if (small.length == 0 || large.length == 0) return lkpOccurances;
    if (small.length > large.length) {
        swap = small;
        small = large;
        large = swap; 
    }
    small.sort((a,b) => a-b);
    large.sort((a,b) => a-b);

    let i = 0, j = 0;
    while (i < small.length && j < large.length) {
        if (small[i] == large[j]) {
            lkpOccurances.push(small[i]);
            i++;
            j = i;
            continue;
        }
        if (small[i] < large[j]) {
            i++
            continue
        }
        j++;
    }
    return lkpOccurances;
};

/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number[]}
 */
var intersect = executionTime(function(nums1, nums2) {
    // a map of the occurances
    let lkpOccurances = {};
    let result = [];
    let i, entriesLength;

    if (nums1.length == 0 || nums2.length == 0) return [];
    if (nums1.length > nums2.length) [nums1, nums2] = [nums2, nums1];
    // iterate over the smallest list and update their counter
    for (i = 0; i < nums1.length; i++) {
        // init the map entry
        if (lkpOccurances[nums1[i]] == undefined) lkpOccurances[nums1[i]] = 0
        // update the occurances
        lkpOccurances[nums1[i]] += 1;
    }
    entriesLength = Object.keys(lkpOccurances).length;
    // iterate over nums2 and only update if there was a match
    for (i = 0; i < nums2.length && entriesLength > 0; i++) {
        if (lkpOccurances[nums2[i]] > 0) {
            if (lkpOccurances[nums2[i]] == 1) {
                delete lkpOccurances[nums2[i]];
                entriesLength -= 1;
            }
            else lkpOccurances[nums2[i]] -= 1;
            result.push(nums2[i]);
        }
    }
    return result;
});

let nums1 = [1,2,2,1], nums2 = [2,2];
console.log(intersect(nums1,nums2));
nums1 = [4,9,5], nums2 = [9,4,9,8,4]
console.log(intersect(nums1,nums2));
// nums1 = [61,24,20,58,95,53,17,32,45,85,70,20,83,62,35,89,5,95,12,86,58,77,30,64,46,13,5,92,67,40,20,38,31,18,89,85,7,30,67,34,62,35,47,98,3,41,53,26,66,40,54,44,57,46,70,60,4,63,82,42,65,59,17,98,29,72,1,96,82,66,98,6,92,31,43,81,88,60,10,55,66,82,0,79,11,81]
// nums2 = [5,25,4,39,57,49,93,79,7,8,49,89,2,7,73,88,45,15,34,92,84,38,85,34,16,6,99,0,2,36,68,52,73,50,77,44,61,48]
console.log(intersect(nums1,nums2));