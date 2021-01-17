/**
 * @param {number[]} nums
 */
var Solution = function(nums) {
    this.original = nums;
    this.array = [...nums];
};

/**
 * Resets the array to its original configuration and return it.
 * @return {number[]}
 */
Solution.prototype.reset = function() {
    this.array = [...this.original];
    return this.array;
};

/**
 * Returns a random shuffling of the array.
 * @return {number[]}
 */
Solution.prototype.shuffle = function() {
    let swap, j;
    for (let i = 0; i < this.array.length; i++) {
        j = (Math.random() * (this.array.length - 1)) >> 0;
        swap = this.array[i];
        this.array[i] = this.array[j];
        this.array[j] = swap;
    }
    return this.array;
};

var obj = new Solution([1,2,3])
console.log(obj.shuffle());
console.log(obj.reset());
console.log(obj.shuffle());