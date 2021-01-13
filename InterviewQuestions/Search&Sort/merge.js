/**
 * @param {number[]} nums1
 * @param {number} m
 * @param {number[]} nums2
 * @param {number} n
 * @return {void} Do not return anything, modify nums1 in-place instead.
 */
var merge = function(nums1, m, nums2, n) {
    let i = 0, j, value;
    if (n == 0) return;
    while (i <= m && m < nums1.length) {
        // find the index we need to insert into
        value = nums2.shift();
        i = findInsertIndex(i, value, nums1, m);
        // bubble the entries so that a 0 is in the insert index
        if (i < m) {
            j = m;
            while (i < j) {
                nums1[j] = nums1[j-1];
                nums1[j-1] = 0;
                j--;
            }
        }
        // insert directly into the zero
        nums1[i] = value;
        i++;
        // update m to have the new number inserted
        m++;
    }
};

var findInsertIndex = function(index, value, nums, searchLength) {
    while (index < searchLength) {
        // check whether the value is smaller than the current index
        if (value < nums[index]) {
            return index;
        }
        // if the insert value is in betweeen the two numbers in the array,
        // we have found our insert index
        if (nums[index] < value && value <= nums[index+1]) {
            return index+1;
        }
        index++;
    }
    // if we reach the end of the search return it at the end
    return searchLength;
}

// the optimal solution
/**
 * @param {number[]} nums1
 * @param {number} m
 * @param {number[]} nums2
 * @param {number} n
 * @return {void} Do not return anything, modify nums1 in-place instead.
 */
var mergeComparingLargestValuesFirst = function(nums1, m, nums2, n) {
    
    // Creating variables first and second to equal the length of nums1(m) and nums2(n). This will allows us to
    // compare the largest number in nums1 and the largest number in nums2. Through these comparisions we will be
    // able to determine which number will replace placeholders in nums1 
    let first = m - 1; 
    let second = n - 1;
    
    // In this for loop we are working backwards to determine which numbers should replace the placeholders in
    // the nums1 array
    for(let i = n + m - 1; i >= 0; i--) {
        
        if(second < 0) { 
            // In the event that we exhaust all of the numbers in the second array, we need to break
            // in order to avoid placing undefined values inside of our nums1 array
            break;
        }
        
        if(nums1[first] > nums2[second]) {
            nums1[i] = nums1[first];
            first--;
        } else {
            nums1[i] = nums2[second]; 
            second--
        }
        
    }    
};

let nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3;
mergeComparingLargestValuesFirst(nums1, m, nums2, n);
console.log(nums1, nums2, m, n);
nums1 = [2,0], m = 1, nums2 = [1], n = 1;
mergeComparingLargestValuesFirst(nums1, m, nums2, n);
console.log(nums1, nums2, m, n);