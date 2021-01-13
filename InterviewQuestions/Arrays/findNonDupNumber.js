function executionTime(func) {
    return function() {
        console.time();
        let results = func.apply(this, arguments);
        console.timeEnd();
        return results;
    }
}

/**
 * @param {number[]} nums
 * @return {number}
 */
var singleNumberXor = executionTime(function(nums) {
    let result = 0;
    for (let i = 0; i < nums.length; i++) {
        result = result ^ nums[i];
    }
    return result;
});
/**
 * @param {number[]} nums
 * @return {number}
 */
var singleNumberTable = executionTime(function(nums) {
    let lkp = {};
    for (let i = 0; i < nums.length; i++) {
        if (lkp[nums[i]] == undefined) {
            lkp[nums[i]] = nums[i];
        } else {
            delete lkp[nums[i]];
        }
    }
    return Object.values(lkp)[0];
});

console.log(singleNumberXor([2,2,1]));
console.log(singleNumberTable([2,2,1]));