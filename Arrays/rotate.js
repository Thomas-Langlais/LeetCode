/**
 * @param {number[]} nums
 * @param {number} k
 * @return {void} Do not return anything, modify nums in-place instead.
 */
var rotate = function(nums, k) {
    let swap;
    if (k > nums.length) {
        k = k%nums.length;
    }
    reverse(nums, 0, nums.length-1);
    reverse(nums, 0, k-1);
    reverse(nums, k, nums.length-1);
};

function reverse(arr, left, right) {
    let swap;
    while (left < right) {
        swap = arr[left];
        arr[left] = arr[right];
        arr[right] = swap;
        left = left + 1;
        right = right - 1;
    }
}

let nums = [1,2,3,4,5,6,7], k = 3;
console.table(nums);rotate(nums, k);console.table(nums);
nums = [-1,-100,3,99], k = 2
console.table(nums);rotate(nums, k);console.table(nums);