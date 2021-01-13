/**
 * @param {number[]} nums1
 * @param {number} m
 * @param {number[]} nums2
 * @param {number} n
 * @return {void} Do not return anything, modify nums1 in-place instead.
 */
var merge = function(nums1, m, nums2, n) {
    let i = 0, j = m, value;
    if (n == 0) return;
    while (i < m && nums2.length > 0) {
        // find the index we need to insert into
        value = nums2.shift();
        i = findInsertIndex(i, value, nums1, m);
        // bubble the entries so that a 0 is in the insert index
        if (i < j) {
            let k = j;
            while (i < k) {
                nums1[k] = nums1[k-1];
                nums1[k-1] = 0;
                k--;
            }
        }
        // insert directly into the zero
        nums1[i] = value;
        j++;
        // update m to have the new number inserted
        m++;
    }
};

var findInsertIndex = function(index, value, nums, searchLength) {
    while (index < searchLength - 1) {
        // if we reach a zero, then insert there
        if (index+1 == searchLength) return searchLength;
        // if the insert value is in betweeen the two numbers in the array,
        // we have found our insert index
        if (nums[index] < value && value <= nums[index+1]) {
            return index+1;
        }
        index++;
    }
}

let nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3;
merge(nums1, m, nums2, n);
console.log(nums1);