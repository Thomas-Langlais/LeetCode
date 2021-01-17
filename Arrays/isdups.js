/**
 * @param {number[]} nums
 * @return {boolean}
 */
var containsDuplicate = function(nums) {
    let lkp = {};
    for (let i = 0; i < nums.length; i++) {
        if (lkp[nums[i]] == undefined) {
            lkp[nums[i]] = true;
        } else {
            return true;
        }
    }
    return false;
};

console.log(containsDuplicate([1,2,3,4]));