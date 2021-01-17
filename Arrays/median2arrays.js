/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number}
 */
var findMedianSortedArrays = function(nums1, nums2) {
    let i = 0, j = 0, median;
    let medianMidPoint = Math.floor((nums1.length + nums2.length) / 2);
    if (nums1.length == 1 && nums2.length == 0) return nums1[0]
    if (nums1.length == 0 && nums2.length == 1) return nums2[0]
    while (i + j < medianMidPoint) {
        if (nums1[i] > nums2[j] || nums1[i] == undefined) {
            median = nums2[j];
            j++;
        } else if (nums2[j] >= nums1[i] || nums2[j] == undefined) {
            median = nums1[i];
            i++;
        }
    }
    
    if (nums1[i] > nums2[j] || nums1[i] == undefined) {
        if ((nums1.length + nums2.length) % 2 == 0) {
            median = (median + nums2[j]) / 2;
        } else {
            median = nums2[j];
        }
    } else if (nums2[j] >= nums1[i] || nums2[j] == undefined) {
        if ((nums1.length + nums2.length) % 2 == 0) {
            median = (median + nums1[i]) / 2;
        } else {
            median = nums1[i];
        }
    }
    return median;
};

let nums1 = [1,2], nums2 = [3,4]
console.log(findMedianSortedArrays(nums1, nums2));