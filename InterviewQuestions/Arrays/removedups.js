var removeDuplicates = function(nums) {
    let newLength = nums.length;
    let newNum;
    let currentNum;

    for (let i = 0; i < nums.length; i++) {
        // check for a new number
        newNum = i == 0 || currentNum != nums[i];
        if (newNum) {
            // set the current number
            currentNum = nums[i];
            // unset the new number flag
            newNum = false;
            continue;
        }

        if (nums[i] === currentNum) {
            // delete the entry
            nums.splice(i, 1);
            // update the new length
            newLength -= 1;
            // reset the index to check again
            i -= 1;
        }
    }
    return newLength;
};

let input = [1,1,2]
console.log(input);
console.log(removeDuplicates(input));
console.log(input);